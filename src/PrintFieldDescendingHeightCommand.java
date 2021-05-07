
import java.util.*;

/**
 * Команда print_field_descending_height, выводит значения поля height всех элементов в порядке убывания
 */
public class PrintFieldDescendingHeightCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public PrintFieldDescendingHeightCommand(CollectionManager collectionManager) {
        super("print_field_descending_height","вывести значения поля height всех элементов в порядке убывания");
        this.collectionManager = collectionManager;
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
            LinkedList<SpaceMarine> list = new LinkedList<>();
            list = collectionManager.getCollection();
            int counter=0;
            double k=0;
            double[] list2=new double[list.size()];
            for (SpaceMarine spaceMarine:list){
                list2[counter]=spaceMarine.getHeight();
                counter+=1;
            }
            for(int i=0;i<counter-2;i++){
                for (int j=i;j<counter-1;j++){
                    if (list2[i]<list2[j]){
                        k=list2[i];
                        list2[i]=list2[j];
                        list2[j]=k;
                    }
                }
            }
            for (int i=0;i<list2.length;i++) {
                Console.println(list2[i]);
            }
        }catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }
        return false;
    }
}
