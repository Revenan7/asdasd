import java.util.concurrent.ThreadLocalRandom;

public class Boar extends Herbivore {
    public Boar(int X, int Y) {
        super(X, Y);
        Name = "Boar";
        Weight = 400.0;
        MaxPerCell = 50;
        MaxSpeed = 2;
        FoodNeeded = 50.0;
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
        return new Boar(X, Y);
    }
}
