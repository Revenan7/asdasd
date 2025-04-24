import java.util.concurrent.ThreadLocalRandom;

public class Horse extends Herbivore {
    public Horse(int X, int Y) {
        super(X, Y);
        Name = "Horse";
        Weight = 400.0;
        MaxPerCell = 20;
        MaxSpeed = 4;
        FoodNeeded = 60.0;
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
        return new Horse(X, Y);
    }
}