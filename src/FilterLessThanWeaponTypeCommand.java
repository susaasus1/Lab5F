

import java.util.LinkedList;

/**
 * Команда filter_less_than_weapon_type, выводит элементы которые меньше задоного
 */
public class FilterLessThanWeaponTypeCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public FilterLessThanWeaponTypeCommand(CollectionManager collectionManager) {
        super("filter_less_than_weapon_type weaponType","вывести элементы, значение поля weaponType которых меньше заданного");
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
            Console.println(Weapon.nameList());
            LinkedList<SpaceMarine> list = new LinkedList<>();
            list = collectionManager.getCollection();
            int counter=0,idx=0;
            if (argument.equalsIgnoreCase("GRENADE_LAUNCHER")) {
                idx=2;
            }
            if (argument.equalsIgnoreCase("HEAVY_FLAMER")) {
                idx=3;
            }
            if (argument.equalsIgnoreCase("COMBI_FLAMER")) {
                idx=1;
            }
            if (argument.equalsIgnoreCase("MULTI_MELTA")) {
                idx=4;
            }
            for (SpaceMarine spaceMarine: list){
                if (spaceMarine.getWeaponType().getInd()<idx){
                    Console.println(spaceMarine);
                    counter+=1;
                }
            }
            if (counter==0) throw new MarineNotFoundException();
            counter=0;
        }catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }catch (MarineNotFoundException exception) {
            Console.printerror("Солдата с такими характеристиками в коллекции нет!");
        }
        return false;
    }
}
