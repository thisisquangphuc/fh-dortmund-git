
// Superclass for Engine
abstract class Engine {
    private int horsepower; // Power of the engine in horsepower
    private String fuelType; // Fuel type (for combustion engines)

    // Constructor
    public Engine(int horsepower, String fuelType) {
        this.horsepower = horsepower;
        this.fuelType = fuelType;
    }

    // Abstract method to get engine type
    public abstract String getEngineType();

    // Getters
    public int getHorsepower() {
        return horsepower;
    }

    public String getFuelType() {
        return fuelType;
    }
}

// Subclass for Combustion Engines
class CombustionEngine extends Engine {
    public CombustionEngine(int horsepower) {
        super(horsepower, "Gasoline");
    }

    @Override
    public String getEngineType() {
        return "Combustion Engine";
    }
}

// Subclass for Electric Engines
class ElectricEngine extends Engine {
    public ElectricEngine(int horsepower) {
        super(horsepower, "Electric");
    }

    @Override
    public String getEngineType() {
        return "Electric Engine";
    }
}

// Subclass for Hybrid Engines
class HybridEngine extends Engine {
    public HybridEngine(int horsepower) {
        super(horsepower, "Hybrid");
    }

    @Override
    public String getEngineType() {
        return "Hybrid Engine";
    }
}

// Class for Manufacture
class Manufacture {
    private String name;
    private String country;

    // Setters and Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

// Superclass for Vehicle
abstract class Vehicle {
    protected Manufacture manufacture;
    protected Engine engine;

    public Vehicle(Manufacture manufacture, Engine engine) {
        this.manufacture = manufacture;
        this.engine = engine;
    }

    // Abstract method to show characteristics
    public abstract void ShowCharacteristics();
}

// Subclass for Internal Combustion Engine Vehicles
class ICEV extends Vehicle {
    public ICEV(Manufacture manufacture, Engine engine) {
        super(manufacture, engine);
    }

    @Override
    public void ShowCharacteristics() {
        System.out.println("ICEV - Manufacture: " + manufacture.getName() +
                ", Country: " + manufacture.getCountry() +
                ", Engine: " + engine.getEngineType() +
                ", Horsepower: " + engine.getHorsepower());
    }
}

// Subclass for Battery Electric Vehicles
class BEV extends Vehicle {
    public BEV(Manufacture manufacture, Engine engine) {
        super(manufacture, engine);
    }

    @Override
    public void ShowCharacteristics() {
        System.out.println("BEV - Manufacture: " + manufacture.getName() +
                ", Country: " + manufacture.getCountry() +
                ", Engine: " + engine.getEngineType() +
                ", Horsepower: " + engine.getHorsepower());
    }
}

// Subclass for Hybrid Vehicles
class HybridV extends Vehicle {
    public HybridV(Manufacture manufacture, Engine engine) {
        super(manufacture, engine);
    }

    @Override
    public void ShowCharacteristics() {
        System.out.println("HybridV - Manufacture: " + manufacture.getName() +
                ", Country: " + manufacture.getCountry() +
                ", Engine: " + engine.getEngineType() +
                ", Horsepower: " + engine.getHorsepower());
    }
}

// Main class to test the hierarchy
public class Main {
    public static void main(String[] args) {
        // Create manufacture instances
        Manufacture toyota = new Manufacture();
        toyota.setName("Toyota");
        toyota.setCountry("Japan");

        Manufacture tesla = new Manufacture();
        tesla.setName("Tesla");
        tesla.setCountry("USA");

        Manufacture honda = new Manufacture();
        honda.setName("Honda");
        honda.setCountry("Japan");

        // Create engine instances
        Engine combustionEngine = new CombustionEngine(150);
        Engine electricEngine = new ElectricEngine(200);
        Engine hybridEngine = new HybridEngine(180);

        // Create an array of Vehicle
        Vehicle[] vehicles = new Vehicle[]{
                new ICEV(toyota, combustionEngine),
                new BEV(tesla, electricEngine),
                new HybridV(honda, hybridEngine)
        };

        // Display characteristics of each vehicle using ShowCharacteristics()
        for (Vehicle vehicle : vehicles) {
            vehicle.ShowCharacteristics();
        }
    }
}
