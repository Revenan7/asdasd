public class Plant {
    private int X, Y;
    private double Weight = 1.0;

    public Plant(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    public double GetWeight() { return Weight; }

    public int GetX() {
        return X;
    }

    public int GetY() {
        return Y;
    }
}