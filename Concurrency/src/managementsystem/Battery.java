package managementsystem;
public class Battery {
	private final int capacity; // in watt-hours
    private int currentCharge; // in watt-hours
    private int ElectricCurrent;

    public Battery(int capacity, int ElecCurrent) {
        this.capacity = capacity;
        this.currentCharge = capacity; //0;
        this.ElectricCurrent = ElecCurrent;
        System.out.println("Current of Battery: " + ElectricCurrent + " Ampere");
    }

    // Synchronized method to charge the battery
    public synchronized void charge(int amount) {
        if (currentCharge + amount > capacity) {
            amount = capacity - currentCharge;
        }
        currentCharge += amount;
        System.out.println(Thread.currentThread().getName() + " charged " + amount + "Wh, Current Charge: " + currentCharge + "Wh");
    }
    
    public synchronized boolean useEnergy(int amount) {
        if (currentCharge >= amount & Main.totalDeviceCurrent <= ElectricCurrent) {
            currentCharge -= amount;
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
}
