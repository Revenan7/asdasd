import java.util.concurrent.ThreadLocalRandom;

public class Sheep extends Herbivore {
    public Sheep(int X, int Y) {
        super(X, Y);
        Name = "Sheep";
        Weight = 70.0;
        MaxPerCell = 140;
        MaxSpeed = 3;
        FoodNeeded = 15.0;
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
        return new Sheep(X, Y);
    }
}