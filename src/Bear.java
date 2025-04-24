import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator {
    public Bear(int X, int Y) {
        super(X, Y);
        Name = "Bear";
        Weight = 500.0;
        MaxPerCell = 5;
        MaxSpeed = 2;
        FoodNeeded = 80.0;

        PreyProbability.put("Boa", 80);
        PreyProbability.put("Horse", 40);
        PreyProbability.put("Deer", 80);
        PreyProbability.put("Rabbit", 80);
        PreyProbability.put("Mouse", 90);
        PreyProbability.put("Goat", 70);
        PreyProbability.put("Sheep", 70);
        PreyProbability.put("Boar", 50);
        PreyProbability.put("Buffalo", 20);
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
        return new Bear(X, Y);
    }
}
