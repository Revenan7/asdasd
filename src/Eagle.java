import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Predator {
    public Eagle(int X, int Y) {
        super(X, Y);
        Name = "Eagle";
        Weight = 6.0;
        MaxPerCell = 20;
        MaxSpeed = 3;
        FoodNeeded = 1.0;

        PreyProbability.put("Fox", 10);
        PreyProbability.put("Rabbit", 90);
        PreyProbability.put("Mouse", 90);
        PreyProbability.put("Duck", 80);
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
        return new Eagle(X, Y);
    }
}
