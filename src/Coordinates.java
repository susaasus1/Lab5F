

import java.util.Objects;

/**
 * X,Y координаты
 */
public class Coordinates {
    private float x;
    private int y;

    public Coordinates(float x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Задает координату X
     * @param x наша координата X
     */
    public void setX(float x) {
        this.x = x;
    }
    /**
     * Задает координату Y
     * @param y наша координата Y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return Координату X
     */
    public float getX() {
        return x;
    }

    /**
     *
     * @return Координату Y
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X=" + x+"," + "Y=" + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.x, x) == 0 && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
