

import java.util.LinkedList;

/**
 * Команда FilterContainsNameCommand, выводит те элементы которые содержат такое же имя как и введеное
 */
public class FilterContainsNameCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterContainsNameCommand(CollectionManager collectionManager) {
        super("filter_contains_name name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collectionManager = collectionManager;
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
            LinkedList<SpaceMarine> list = new LinkedList<>();
            list = collectionManager.getCollection();
            int counter=0;
            for (SpaceMarine spaceMarine:list){
                if (spaceMarine.getName().equalsIgnoreCase(argument)){
                    Console.println(spaceMarine);
                    counter+=1;
                }
            }
            if (counter==0) throw new MarineNotFoundException();
            counter=0;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (MarineNotFoundException exception) {
            Console.printerror("Солдат не найден!");
        }
        return false;
    }
}
