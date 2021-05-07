

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * Глвный персонаж,сохраняется в коллекцию
 */
public class SpaceMarine implements Comparator<SpaceMarine> {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Float health;
    private double height;
    private AstartesCategory category;
    private Weapon weaponType;
    private Chapter chapter;

    public SpaceMarine(Long id, String name, Coordinates coordinates, LocalDate creationDate, Float health, double height, AstartesCategory category, Weapon weaponType, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.height = height;
        this.category = category;
        this.weaponType = weaponType;
        this.chapter = chapter;
    }

    public SpaceMarine() {
    }

    /**
     * Задает ID солдата
     * @param id ID солдата
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Задает имя солдата
     * @param name Имя солдата
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Задает координаты персонажа
     * @param coordinates Координаты солдата
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Задает дату создания персонажа
     * @param creationDate Дата создания солдата
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Задает здоровье солдата
     * @param health Здоровье солдата
     */
    public void setHealth(Float health) {
        this.health = health;
    }

    /**
     * Задает рост солдата
     * @param height Рост солдата
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Задает категорию солдата
     * @param category Категория солдата
     */
    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    /**
     * Задает тип оружия солдата
     * @param weaponType Тип оружия солдата
     */
    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * Задает часть солдата
     * @param chapter Часть солдата
     */
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    /**
     *
     * @return ID солдата
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return Имя солдата
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Координаты солдата
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     *
     * @return Дату создания солдата
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @return Здоровье солдата
     */
    public Float getHealth() {
        return health;
    }

    /**
     *
     * @return Рост солдата
     */
    public double getHeight() {
        return height;
    }

    /**
     *
     * @return Категорию солдата
     */
    public AstartesCategory getCategory() {
        return category;
    }

    /**
     *
     * @return Тип оружия солдата
     */
    public Weapon getWeaponType() {
        return weaponType;
    }

    /**
     *
     * @return Часть солдата
     */
    public Chapter getChapter() {
        return chapter;
    }



    @Override
    public String toString() {
        return  "ID:" + id +
                "\n\tName: " + name  +
                "\n\tCoordinates: " + coordinates +
                "\n\tCreationDate(YYYY-MM-DD): " + creationDate +
                "\n\tHealth: " + health +
                "\n\tHeight: " + height +
                "\n\tCategory: " + category +
                "\n\tWeaponType: " + weaponType +
                "\n\tChapter: " + chapter
                ;
    }

    @Override
    public int compare(SpaceMarine o1, SpaceMarine o2) {
       return o1.getId().compareTo(o2.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return Double.compare(that.height, height) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(health, that.health) && category == that.category && weaponType == that.weaponType && Objects.equals(chapter, that.chapter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, height, category, weaponType, chapter);
    }


    public int compareTo(SpaceMarine marineObj) {
        return id.compareTo(marineObj.getId());
    }
}
