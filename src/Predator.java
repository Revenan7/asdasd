import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {
    protected Map<String, Integer> PreyProbability;

    public Predator(int X, int Y) {
        super(X, Y);
        PreyProbability = new HashMap<>();
    }

    @Override
    void Eat(List<Animal> Animals, List<Plant> Plants) {
        synchronized (Animals) {
            for (Animal Prey : Animals) {
                if (Prey != this && Prey.IsAlive() && Prey.X == this.X && Prey.Y == this.Y) {
                    Integer Probability = PreyProbability.get(Prey.Name);
                    if (Probability != null && ThreadLocalRandom.current().nextInt(100) < Probability) {
                        Prey.IsAlive = false;
                        return;
                    }
                }
            }
        }
    }
}
