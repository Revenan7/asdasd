import java.util.concurrent.ThreadLocalRandom;

public class Goat extends Herbivore {
    public Goat(int X, int Y) {
        super(X, Y);
        Name = "Goat";
        Weight = 60.0;
        MaxPerCell = 140;
        MaxSpeed = 3;
        FoodNeeded = 10.0;
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
        return new Goat(X, Y);
    }
}