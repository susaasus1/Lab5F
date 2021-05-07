

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Класс управляющий коллекцией
 */
public class CollectionManager {

    private LinkedList<SpaceMarine> marinesCollection = new LinkedList<>();
    private LocalDate lastInitTime;
    private LocalDate lastSaveTime;
    private FileParser fileParser;

    public CollectionManager(FileParser fileParser) throws IOException {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileParser = fileParser;
        loadCollection();
    }

    /**
     * @return Возвращает коллекцию
     */
    public LinkedList<SpaceMarine> getCollection() {
        return marinesCollection;
    }

    /**
     *
     * @return Возвращает последнее время иницилизации
     */
    public LocalDate getLastInitTime() {
        return lastInitTime;
    }

    /**
     *
     * @return Возвращает последнее время сохранения
     */
    public LocalDate getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     *
     * @return Возвращает информацию о типе коллекции
     */
    public String collectionType() {
        return marinesCollection.getClass().getName();
    }

    /**
     *
     * @return Возвращает размер коллекции
     */
    public int collectionSize() {
        return marinesCollection.size();
    }

    /**
     *
     * @return Возвращает первый элемент коллекции
     */
    public SpaceMarine getFirst() {
        if (marinesCollection.isEmpty()) return null;
        return marinesCollection.get(0);
    }

    /**
     *
     * @return Возвращает последний элемент коллекции
     */
    public SpaceMarine getLast() {
        if (marinesCollection.isEmpty()) return null;
        return marinesCollection.getLast();
    }

    /**
     *
     * @param id ID нашего солдата
     * @return  Вовзращает элемент коллекции по его ID или null если не найден
     */
    public SpaceMarine getById(Long id) {
        for (SpaceMarine marine : marinesCollection) {
            if (marine.getId().equals(id)) return marine;
        }
        return null;
    }

    /**
     *
     * @param marineToFind Нужный нам солдат
     * @return Возвращает солдата если такой есть в коллекции иначе вернет null
     */
    public SpaceMarine getByValue(SpaceMarine marineToFind) {
        for (SpaceMarine marine : marinesCollection) {
            if (marine.equals(marineToFind)) return marine;
        }
        return null;
    }

    /**
     * Добавляет солдата в коллекцию
     * @param marine Солдат для добавления в коллекцию
     */
    public void addToCollection(SpaceMarine marine) {
        marinesCollection.add(marine);
    }

    /**
     * Удаляет солдата из коллекции
     * @param marine Солдат для удаления из коллекции
     */
    public void removeFromCollection(SpaceMarine marine) {
        marinesCollection.remove(marine);
    }

    /**
     * Удаляет всех солдат превышающих данного
     * @param marineToCompare Солдат для параметра по которому удалять
     */
    public void removeGreater(SpaceMarine marineToCompare) {
        marinesCollection.removeIf(marine -> marine.compareTo(marineToCompare) > 0);

    }

    /**
     * Очищает коллекцию
     */
    public void clearCollection() {
        marinesCollection.clear();
    }

    /**
     * Сохраняет коллекцию
     */
    public void saveCollection() {
        fileParser.unparse(marinesCollection);
        lastSaveTime = LocalDate.now();
    }

    /**
     *
     * @return Возвращает новый уникальный ID
     */
    public Long generateNextId() {
        if (marinesCollection.isEmpty()) return 1L;
        return marinesCollection.getLast().getId()+1;
    }

    /**
     * Загружает коллекцию
     * @throws IOException исключение ввода/вывода
     */
    private void loadCollection() throws IOException {
        marinesCollection = fileParser.parse();
        lastInitTime = LocalDate.now();
    }

    public String toString() {

        Collections.sort(marinesCollection, new Comparator<SpaceMarine>() {
            @Override
            public int compare(SpaceMarine o1, SpaceMarine o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        if (marinesCollection.isEmpty()) return "Коллекция пуста!";

        String info = "";
        for (SpaceMarine marine : marinesCollection) {
            info += marine;
            if (marine != marinesCollection.getLast()) info += "\n\n";
        }

        return info;
    }

}
