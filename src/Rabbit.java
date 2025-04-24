import java.util.concurrent.ThreadLocalRandom;

public class Rabbit extends Herbivore {
    public Rabbit(int X, int Y) {
        super(X, Y);
        Name = "Rabbit";
        Weight = 2.0;
        MaxPerCell = 150;
        MaxSpeed = 2;
        FoodNeeded = 0.45;
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
        return new Rabbit(X, Y);
    }
}