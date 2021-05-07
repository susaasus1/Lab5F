

import java.util.Objects;

/**
 * Часть солдата
 */
public class Chapter {
    private String name;
    private String parentLegion;
    private Integer marinesCount;
    private String world;

    public Chapter(String name, String parentLegion, Integer marinesCount, String world) {
        this.name = name;
        this.parentLegion = parentLegion;
        this.marinesCount = marinesCount;
        this.world = world;
    }

    /**
     * Задает имя служебной части
     * @param name Имя служебной части
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Задает имя родительской служебной части
     * @param parentLegion Родительская служебная часть
     */
    public void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }

    /**
     * Задает количество солдат в части
     * @param marinesCount Количество солдат в части
     */
    public void setMarinesCount(Integer marinesCount) {
        this.marinesCount = marinesCount;
    }

    /**
     * Задает мир служебной части
     * @param world Название мира служебной части
     */
    public void setWorld(String world) {
        this.world = world;
    }

    /**
     *
     * @return Имя служебной части
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Родительскую служебную часть
     */
    public String getParentLegion() {
        return parentLegion;
    }

    /**
     *
     * @return Количество солдат в части
     */
    public Integer getMarinesCount() {
        return marinesCount;
    }

    /**
     *
     * @return Название мира служебной части
     */
    public String getWorld() {
        return world;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(name, chapter.name) && Objects.equals(parentLegion, chapter.parentLegion) && Objects.equals(marinesCount, chapter.marinesCount) && Objects.equals(world, chapter.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parentLegion, marinesCount, world);
    }

    @Override
    public String toString() {
        return
                "\n\t\tname: " + name  +
                "\n\t\tparentLegion: " + parentLegion  +
                "\n\t\tmarinesCount: " + marinesCount +
                "\n\t\tworld: " + world
                ;
    }
}
