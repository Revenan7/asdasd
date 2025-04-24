import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Predator {
    public Wolf(int X, int Y) {
        super(X, Y);
        Name = "Wolf";
        Weight = 50.0;
        MaxPerCell = 30;
        MaxSpeed = 3;
        FoodNeeded = 8.0;
        PreyProbability.put("Horse", 10);
        PreyProbability.put("Deer", 15);
        PreyProbability.put("Rabbit", 60);
        PreyProbability.put("Mouse", 80);
        PreyProbability.put("Goat", 60);
        PreyProbability.put("Sheep", 70);
        PreyProbability.put("Boar", 15);
        PreyProbability.put("Buffalo", 10);
        PreyProbability.put("Duck", 40);
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
        return new Wolf(X, Y);
    }
}