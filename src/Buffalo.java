import java.util.concurrent.ThreadLocalRandom;

public class Buffalo extends Herbivore {
    public Buffalo(int X, int Y) {
        super(X, Y);
        Name = "Buffalo";
        Weight = 700.0;
        MaxPerCell = 10;
        MaxSpeed = 3;
        FoodNeeded = 100.0;
    }

    @Override
    void Move() {
        int Dx = ThreadLocalRandom.current().nextInt(-MaxSpeed, MaxSpeed + 1);
        int Dy = ThreadLocalRandom.current().nextInt(-MaxSpeed, MaxSpeed + 1);
        X = Math.max(0, Math.min(IslandParams.Width - 1, X + Dx));
        Y = Math.max(0, Math.min(IslandParams.Height - 1, Y + Dy));
    }

    @Override
    Animal Reproduce() {
        return new Buffalo(X, Y);
    }
}
