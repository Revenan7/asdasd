import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivore {
    public Mouse(int X, int Y) {
        super(X, Y);
        Name = "Mouse";
        Weight = 0.05;
        MaxPerCell = 500;
        MaxSpeed = 1;
        FoodNeeded = 0.01;
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
        return new Mouse(X, Y);
    }
}
