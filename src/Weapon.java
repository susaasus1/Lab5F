
/**
 * Перечесление оружия солдата
 */
public enum Weapon {
    COMBI_FLAMER(1),
    GRENADE_LAUNCHER(2),
    HEAVY_FLAMER(3),
    MULTI_MELTA(4);

    private int ind;

    Weapon(int ind) {
        this.ind=ind;
    }
    /**
     * Генерирует крассивый вывод enum
     * @return Возвращает все элементы enum через запятую
     */
    public static String nameList() {
        String nameList = "";
        for (Weapon weaponType : values()) {
            nameList += weaponType.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }

    /**
     *
     * @return индекс оружия солдата
     */
    public int getInd() {
        return ind;
    }
}
