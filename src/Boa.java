import java.util.concurrent.ThreadLocalRandom;

public class Boa extends Predator {
    public Boa(int X, int Y) {
        super(X, Y);
        Name = "Boa";
        Weight = 15.0;
        MaxPerCell = 30;
        MaxSpeed = 1;
        FoodNeeded = 3.0;

        PreyProbability.put("Fox", 15);
        PreyProbability.put("Rabbit", 20);
        PreyProbability.put("Mouse", 40);
        PreyProbability.put("Duck", 10);
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
        return new Boa(X, Y);
    }
}
