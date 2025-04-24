import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore {
    public Duck(int X, int Y) {
        super(X, Y);
        Name = "Duck";
        Weight = 1.0;
        MaxPerCell = 200;
        MaxSpeed = 4;
        FoodNeeded = 0.15;
    }

    @Override
    void Eat(List<Animal> Animals, List<Plant> Plants) {
        synchronized (Animals) {
            for (Animal Prey : Animals) {
                if (Prey != this && Prey.IsAlive() && Prey.X == this.X && Prey.Y == this.Y && Prey.Name.equals("Caterpillar")) {
                    if (ThreadLocalRandom.current().nextInt(100) < 90) {
                        Prey.IsAlive = false;
                        Hunger = 0.0;
                        return;
                    }
                }
            }
        }
        super.Eat(Animals, Plants);
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
        return new Duck(X, Y);
    }
}

