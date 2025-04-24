import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends Herbivore {
    public Caterpillar(int X, int Y) {
        super(X, Y);
        Name = "Caterpillar";
        Weight = 0.01;
        MaxPerCell = 1000;
        MaxSpeed = 0;
        FoodNeeded = 0.001;
    }

    @Override
    void Eat(List<Animal> Animals, List<Plant> Plants) {
        synchronized (Plants) {
            Iterator<Plant> IteratorInstance = Plants.iterator();
            while (IteratorInstance.hasNext()) {
                Plant PlantInstance = IteratorInstance.next();
                if (PlantInstance.GetX() == this.X && PlantInstance.GetY() == this.Y) {
                    IteratorInstance.remove();
                    Hunger = 0.0;
                    return;
                }
            }
        }
        UpdateHunger();
    }

    @Override
    void Move() {
    }

    @Override
    Animal Reproduce() {
        return new Caterpillar(X, Y);
    }
}