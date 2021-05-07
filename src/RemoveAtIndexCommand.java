

import java.util.LinkedList;

/**
 * Команда remove_at_index, удаляет элемент находящийся в заданной позиции коллекции
 */
public class RemoveAtIndexCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveAtIndexCommand(CollectionManager collectionManager) {
        super("remove_at index ", "удалить элемент, находящийся в заданной позиции коллекции (index)");
        this.collectionManager=collectionManager;
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
            int index = Integer.parseInt(argument);
            SpaceMarine marineToRemove;
            LinkedList<SpaceMarine> list = new LinkedList<>();
            list = collectionManager.getCollection();
            int size = list.size();
            if (index <= size - 1) {
                marineToRemove = list.remove(index);
                collectionManager.removeFromCollection(marineToRemove);
            } else {
                throw new IndexOutOfBoundsException();
            }
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (IndexOutOfBoundsException exception) {
            Console.printerror("Такого индекса в коллекции нет!");
        }
        return false;
    }
}
