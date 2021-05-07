
import java.time.LocalDate;

/**
 * Команда remove_greater, удаляет элементы из коллекции превышающие заданный
 */
public class RemoveGreaterCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private MarineChecker marineAsker;

    public RemoveGreaterCommand(CollectionManager collectionManager, MarineChecker marineAsker) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
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
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            SpaceMarine marineToFind = new SpaceMarine(
                    collectionManager.generateNextId(),
                    marineAsker.askName(),
                    marineAsker.askCoordinates(),
                    LocalDate.now(),
                    marineAsker.askHealth(),
                    marineAsker.askHeight(),
                    marineAsker.askCategory(),
                    marineAsker.askWeaponType(),
                    marineAsker.askChapter()
            );
            SpaceMarine marineFromCollection = collectionManager.getByValue(marineToFind);
            if (marineFromCollection == null) throw new MarineNotFoundException();
            collectionManager.removeGreater(marineFromCollection);
            Console.println("Солдаты успешно удалены!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (MarineNotFoundException exception) {
            Console.printerror("Солдата с такими характеристиками в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
