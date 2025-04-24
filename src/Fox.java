import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator {
    public Fox(int X, int Y) {
        super(X, Y);
        Name = "Fox";
        Weight = 8.0;
        MaxPerCell = 30;
        MaxSpeed = 2;
        FoodNeeded = 2.0;

        PreyProbability.put("Rabbit", 70);
        PreyProbability.put("Mouse", 90);
        PreyProbability.put("Duck", 60);
        PreyProbability.put("Caterpillar", 40);
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
        return new Fox(X, Y);
    }
}

