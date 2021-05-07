
import java.time.LocalDate;



/**
 * Команда update, обновляет элемент коллекции по ID
 */
public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private MarineChecker marineAsker;

    public UpdateCommand(CollectionManager collectionManager, MarineChecker marineAsker) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.collectionManager = collectionManager;
        this.marineAsker = marineAsker;
    }

    /**
     * Исполняет команду
     * @return Статус завершения команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Long id = Long.parseLong(argument);
            SpaceMarine oldMarine = collectionManager.getById(id);
            if (oldMarine == null) throw new MarineNotFoundException();

            String name = oldMarine.getName();
            Coordinates coordinates = oldMarine.getCoordinates();
            LocalDate creationDate = oldMarine.getCreationDate();
            Float health = oldMarine.getHealth();
            double height =oldMarine.getHeight();
            AstartesCategory category = oldMarine.getCategory();
            Weapon weaponType = oldMarine.getWeaponType();
            Chapter chapter = oldMarine.getChapter();

            collectionManager.removeFromCollection(oldMarine);

            if (marineAsker.askQuestion("Хотите изменить имя солдата?")) name = marineAsker.askName();
            if (marineAsker.askQuestion("Хотите изменить координаты солдата?")) coordinates = marineAsker.askCoordinates();
            if (marineAsker.askQuestion("Хотите изменить здоровье солдата?")) health = marineAsker.askHealth();
            if (marineAsker.askQuestion("Хотите изменить рост?")) height=marineAsker.askHeight();
            if (marineAsker.askQuestion("Хотите изменить категорию солдата?")) category = marineAsker.askCategory();
            if (marineAsker.askQuestion("Хотите изменить оружие дальнего боя солдата?")) weaponType = marineAsker.askWeaponType();
            if (marineAsker.askQuestion("Хотите изменить орден солдата?")) chapter = marineAsker.askChapter();

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
            Console.println("Солдат успешно изменен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (MarineNotFoundException exception) {
            Console.printerror("Солдата с таким ID в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
