

import java.time.LocalDate;


/**
 * Команда add, добавляет новый элемент в коллекцию
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private MarineChecker marineAsker;

    public AddCommand(CollectionManager collectionManager, MarineChecker marineAsker) {
        super("add {element}", "добавить новый элемент в коллекцию");
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
            collectionManager.addToCollection(new SpaceMarine(
                    collectionManager.generateNextId(),
                    marineAsker.askName(),
                    marineAsker.askCoordinates(),
                    LocalDate.now(),
                    marineAsker.askHealth(),
                    marineAsker.askHeight(),
                    marineAsker.askCategory(),
                    marineAsker.askWeaponType(),
                    marineAsker.askChapter()
            ));
            Console.println("Солдат успешно добавлен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}