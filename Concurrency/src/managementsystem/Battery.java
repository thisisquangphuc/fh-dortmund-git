package managementsystem;
public class Battery {
	private int id;
	private final int capacity; // in watt-hours
    private int currentCharge; // in watt-hours
    private int ElectricCurrent;

    public Battery(int id, int capacity, int currentCharge, int elecCurrent) {
        this.id = id;
    	this.capacity = capacity;
        this.currentCharge = currentCharge; //capacity;
        this.ElectricCurrent = elecCurrent;
        System.out.println("Current of Battery" + this.id + ": " + ElectricCurrent + " Ampere");
    }

    // Synchronized method to charge the battery
    public synchronized void charge(int amount, EnergySource chargingEnergy) {
        if (this.currentCharge + amount > capacity) {
            amount = capacity - this.currentCharge;
        }
        this.currentCharge += amount;
        System.out.format("Battery%d is charged %dWh by %s energy, Current Charge: %dWh - %d%%.\n", 
        		this.id, amount, chargingEnergy.getName(), currentCharge, (int)(100*currentCharge)/capacity);
        if (currentCharge == capacity) {
        	System.out.format("Charging Battery%d DONE.\n", this.id);
        }
    }
    
    public synchronized boolean useEnergy(int amount) {
        if (this.currentCharge >= amount & Main.totalDeviceCurrent <= ElectricCurrent) {
            this.currentCharge -= amount;
            System.out.println(Thread.currentThread().getName() + " used " + amount + "Wh, Remaining Charge: " + currentCharge + "Wh");
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + " attempted to use " + amount + "Wh but insufficient charge!");
            return false;
        }
    }
    
    public int getCurrentCharge() {
        return currentCharge;
    }

    public int getCapacity() {
        return capacity;
    }
    
    public int getCurrent() {
        return ElectricCurrent;
    }
    public int getId() {
    	return id;
    }
}
