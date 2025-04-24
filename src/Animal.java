import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    protected String Name;
    protected double Weight;
    protected int MaxPerCell;
    protected int MaxSpeed;
    protected double FoodNeeded;
    protected int X, Y;
    protected boolean IsAlive = true;
    protected double Hunger = 0.0;

    public Animal(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    abstract void Eat(List<Animal> Animals, List<Plant> Plants);
    abstract void Move();
    abstract Animal Reproduce();

    public void UpdateHunger() {
        Hunger += FoodNeeded / 10;
        if (Hunger >= FoodNeeded * 10) {
            IsAlive = false;
        }
        if (ThreadLocalRandom.current().nextInt(100) < 1) {
            IsAlive = false;
        }
    }

    public boolean IsAlive() { return IsAlive; }
    public double GetWeight() { return Weight; }
    public String GetSymbol() { return Name.substring(0, 1); }
    public int GetX() { return X; }
    public int GetY() { return Y; }
    public String GetName() { return Name; }
}
