

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Команда insert_at_index, добавляет новый элемент в заданную позицию
 */
public class InsertAtIndexCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private MarineChecker marineAsker;

    public InsertAtIndexCommand(CollectionManager collectionManager, MarineChecker marineAsker) {
        super("insert_at index","добавить новый элемент в заданную позицию");
        this.collectionManager=collectionManager;
        this.marineAsker=marineAsker;
    }
    /**
     * Исполняет команду
     * @return Статус завершения команды
     */
    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            int index=Integer.parseInt(argument);
            SpaceMarine oldMarine;
            LinkedList<SpaceMarine> list = new LinkedList<>();
            list = collectionManager.getCollection();
            int size = list.size();
            if (index <= size - 1) {
                oldMarine = list.remove(index);
            } else {
                throw new IndexOutOfBoundsException();
            }
            if (oldMarine == null) throw new MarineNotFoundException();
            SpaceMarine marineToFind = new SpaceMarine(
                    oldMarine.getId(),
                    marineAsker.askName(),
                    marineAsker.askCoordinates(),
                    LocalDate.now(),
                    marineAsker.askHealth(),
                    marineAsker.askHeight(),
                    marineAsker.askCategory(),
                    marineAsker.askWeaponType(),
                    marineAsker.askChapter()
            );
            Long id =marineToFind.getId();
            String name = marineToFind.getName();
            Coordinates coordinates = marineToFind.getCoordinates();
            LocalDate creationDate = marineToFind.getCreationDate();
            Float health = marineToFind.getHealth();
            double height =marineToFind.getHeight();
            AstartesCategory category = marineToFind.getCategory();
            Weapon weaponType = marineToFind.getWeaponType();
            Chapter chapter = marineToFind.getChapter();
            collectionManager.removeFromCollection(oldMarine);
            collectionManager.addToCollection(new SpaceMarine(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    health,
                    height,
                    category,
                    weaponType,
                    chapter
            ));

        } catch (WrongAmountOfElementsException exception){
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception){
            Console.printerror("Коллекция пуста!");
        } catch (MarineNotFoundException exception){
            Console.printerror("Солдата с такими характеристиками в коллекции нет!");
        } catch (IncorrectInputInScriptException e) {

        } catch (IndexOutOfBoundsException exception){
            Console.printerror("Такого индекса в коллекции нет!");
        }

        return false;
    }
}
