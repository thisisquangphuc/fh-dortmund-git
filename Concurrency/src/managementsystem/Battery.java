package managementsystem;
public class Battery {
	private int id;
	private final int capacity; // in watt-hours
    private int currentCharge; // in watt-hours
    private int ElectricCurrent;

    public Battery(int capacity, int elecCurrent) {
        this.capacity = capacity;
        this.currentCharge = 0; //capacity;
        this.ElectricCurrent = elecCurrent;
        this.id = Main.numOfBatteries;
        Main.numOfBatteries += 1;
        System.out.println("Current of Battery" + this.id + ": " + ElectricCurrent + " Ampere");
    }

    // Synchronized method to charge the battery
    public synchronized void charge(int amount) {
        if (this.currentCharge + amount > capacity) {
            amount = capacity - this.currentCharge;
        }
        this.currentCharge += amount;
        System.out.format("%s charged Battery%d %dWh, Current Charge: %dWh.\n", 
        		Thread.currentThread().getName(), this.id, amount, currentCharge);
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
