package Place;

public class Area {
    private int x;
    private int y;
    private int z;
    private int r;

    public Area(int x1, int y1, int z1, int r1, boolean isCreature) {
        if (isCreature) {
            this.r = 1;
        } else {
            this.r = r1;
        }
        this.x = x1;
        this.y = y1;
        this.z = z1;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getR() {
        return r;
    }
}
