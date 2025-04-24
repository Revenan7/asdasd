import java.util.*;
import java.util.concurrent.*;

public class Main {
    private List<Animal> Animals = Collections.synchronizedList(new ArrayList<>());
    private List<Plant> Plants = Collections.synchronizedList(new ArrayList<>());
    private ScheduledExecutorService Scheduler = Executors.newScheduledThreadPool(3);
    private ExecutorService AnimalPool = Executors.newFixedThreadPool(10);
    private volatile boolean Running = true;
    private int Ticks = 0;

    public Main() {
        InitializeIsland();
    }

    private void InitializeIsland() {
        Random Rand = new Random();

        // Хищники
        for (int i = 0; i < IslandParams.InitialWolves; i++) {
            Animals.add(new Wolf(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialBoas; i++) {
            Animals.add(new Boa(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialFoxes; i++) {
            Animals.add(new Fox(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialBears; i++) {
            Animals.add(new Bear(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialEagles; i++) {
            Animals.add(new Eagle(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }

        // Травоядные
        for (int i = 0; i < IslandParams.InitialHorses; i++) {
            Animals.add(new Horse(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialDeer; i++) {
            Animals.add(new Deer(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialRabbits; i++) {
            Animals.add(new Rabbit(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialMice; i++) {
            Animals.add(new Mouse(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialGoats; i++) {
            Animals.add(new Goat(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialSheep; i++) {
            Animals.add(new Sheep(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialBoars; i++) {
            Animals.add(new Boar(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialBuffalo; i++) {
            Animals.add(new Buffalo(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialDucks; i++) {
            Animals.add(new Duck(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
        for (int i = 0; i < IslandParams.InitialCaterpillars; i++) {
            Animals.add(new Caterpillar(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }

        // Растения
        for (int i = 0; i < IslandParams.InitialPlants; i++) {
            Plants.add(new Plant(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
        }
    }

    private void GrowPlants() {
        Random Rand = new Random();
        synchronized (Plants) {
            if (Plants.size() < IslandParams.MaxTotalPlants) {
                for (int i = 0; i < 3; i++) {
                    Plants.add(new Plant(Rand.nextInt(IslandParams.Width), Rand.nextInt(IslandParams.Height)));
                }
            }
        }
    }

    private void AnimalLifeCycle() {
        List<Future<?>> Futures = new ArrayList<>();
        List<Animal> AnimalsSnapshot = new ArrayList<>(Animals);

        for (Animal AnimalInstance : AnimalsSnapshot) {
            if (AnimalInstance.IsAlive()) {
                Futures.add(AnimalPool.submit(() -> {
                    AnimalInstance.Eat(Animals, Plants);
                    AnimalInstance.Move();
                    synchronized (Animals) {
                        Map<String, Integer> CellCount = new HashMap<>();
                        Map<String, Integer> TotalCount = new HashMap<>();
                        Animals.forEach(A -> {
                            if (A.IsAlive()) {
                                TotalCount.merge(A.Name, 1, Integer::sum);
                                if (A.GetX() == AnimalInstance.GetX() && A.GetY() == AnimalInstance.GetY()) {
                                    CellCount.merge(A.Name, 1, Integer::sum);
                                }
                            }
                        });
                        boolean CanReproduce = false;

                        int ReproduceChance =
                                (AnimalInstance.Name.equals("Wolf") || AnimalInstance.Name.equals("Boa") ||
                                        AnimalInstance.Name.equals("Fox") || AnimalInstance.Name.equals("Bear") ||
                                        AnimalInstance.Name.equals("Eagle"))
                                        ? 10 : 15;


                        if (AnimalInstance.Name.equals("Wolf")) {
                            CanReproduce = CellCount.getOrDefault("Wolf", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Wolf", 0) < IslandParams.MaxWolves;
                        } else if (AnimalInstance.Name.equals("Boa")) {
                            CanReproduce = CellCount.getOrDefault("Boa", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Boa", 0) < IslandParams.MaxBoas;
                        } else if (AnimalInstance.Name.equals("Fox")) {
                            CanReproduce = CellCount.getOrDefault("Fox", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Fox", 0) < IslandParams.MaxFoxes;
                        } else if (AnimalInstance.Name.equals("Bear")) {
                            CanReproduce = CellCount.getOrDefault("Bear", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Bear", 0) < IslandParams.MaxBears;
                        } else if (AnimalInstance.Name.equals("Eagle")) {
                            CanReproduce = CellCount.getOrDefault("Eagle", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Eagle", 0) < IslandParams.MaxEagles;
                        } else if (AnimalInstance.Name.equals("Horse")) {
                            CanReproduce = CellCount.getOrDefault("Horse", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Horse", 0) < IslandParams.MaxHorses;
                        } else if (AnimalInstance.Name.equals("Deer")) {
                            CanReproduce = CellCount.getOrDefault("Deer", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Deer", 0) < IslandParams.MaxDeer;
                        } else if (AnimalInstance.Name.equals("Rabbit")) {
                            CanReproduce = CellCount.getOrDefault("Rabbit", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Rabbit", 0) < IslandParams.MaxRabbits;
                        } else if (AnimalInstance.Name.equals("Mouse")) {
                            CanReproduce = CellCount.getOrDefault("Mouse", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Mouse", 0) < IslandParams.MaxMice;
                        } else if (AnimalInstance.Name.equals("Goat")) {
                            CanReproduce = CellCount.getOrDefault("Goat", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Goat", 0) < IslandParams.MaxGoats;
                        } else if (AnimalInstance.Name.equals("Sheep")) {
                            CanReproduce = CellCount.getOrDefault("Sheep", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Sheep", 0) < IslandParams.MaxSheep;
                        } else if (AnimalInstance.Name.equals("Boar")) {
                            CanReproduce = CellCount.getOrDefault("Boar", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Boar", 0) < IslandParams.MaxBoars;
                        } else if (AnimalInstance.Name.equals("Buffalo")) {
                            CanReproduce = CellCount.getOrDefault("Buffalo", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Buffalo", 0) < IslandParams.MaxBuffalo;
                        } else if (AnimalInstance.Name.equals("Duck")) {
                            CanReproduce = CellCount.getOrDefault("Duck", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Duck", 0) < IslandParams.MaxDucks;
                        } else if (AnimalInstance.Name.equals("Caterpillar")) {
                            CanReproduce = CellCount.getOrDefault("Caterpillar", 0) < AnimalInstance.MaxPerCell && TotalCount.getOrDefault("Caterpillar", 0) < IslandParams.MaxCaterpillars;
                        }

                        if (CanReproduce && ThreadLocalRandom.current().nextInt(100) < ReproduceChance) {
                            Animal Offspring = AnimalInstance.Reproduce();
                            Animals.add(Offspring);
                        }
                    }
                }));
            }
        }

        for (Future<?> FutureInstance : Futures) {
            try {
                FutureInstance.get();
            } catch (Exception E) {
                E.printStackTrace();
            }
        }

        synchronized (Animals) {
            Animals.removeIf(A -> !A.IsAlive());
        }
    }

    private void PrintStats() {
        Map<String, Integer> AnimalCount = new HashMap<>();
        synchronized (Animals) {
            Animals.forEach(A -> AnimalCount.merge(A.Name, 1, Integer::sum));
            if (AnimalCount.size() == 0) Running = false;
        }
        System.out.println("Тик " + Ticks +  "\n" + "Растения: " + Plants.size() + ", Животные: " + Animals.size());
        AnimalCount.forEach((Name, Count) -> System.out.println(Name + ": " + Count));
        System.out.println("------------------------");
        Ticks++;
    }

    public void Start() {
        Scheduler.scheduleAtFixedRate(this::GrowPlants, 0, 5, TimeUnit.SECONDS);
        Scheduler.scheduleAtFixedRate(this::AnimalLifeCycle, 0, 5, TimeUnit.SECONDS);
        Scheduler.scheduleAtFixedRate(this::PrintStats, 0, 5, TimeUnit.SECONDS);

        while (Running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException E) {
                E.printStackTrace();
            }
        }
        Stop();
    }

    public void Stop() {
        Running = false;
        Scheduler.shutdown();
        AnimalPool.shutdown();
    }

    public static void main(String[] Args) {
        new Main().Start();
    }
}
