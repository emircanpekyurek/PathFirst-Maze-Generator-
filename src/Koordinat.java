
public class Koordinat {
    private int x;
    private int y;

    public Koordinat() {}

    public Koordinat(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Koordinat{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}