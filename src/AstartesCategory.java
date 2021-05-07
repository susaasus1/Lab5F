

/**
 * Перечесление категорий солдата
 */
public enum AstartesCategory {
    DREADNOUGHT,
    INCEPTOR,
    LIBRARIAN,
    CHAPLAIN,
    HELIX;

    /**
     * Генерирует крассивый вывод enum
     * @return Возвращает все элементы enum через запятую
     */

    public static String nameList() {
        String nameList = "";
        for (AstartesCategory category : values()) {
            nameList += category.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
