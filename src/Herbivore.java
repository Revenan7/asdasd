import java.util.Iterator;
import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(int X, int Y) {
        super(X, Y);
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
}
