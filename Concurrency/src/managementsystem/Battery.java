package managementsystem;
public class Battery {
	private int id;
	private final int capacity; // in watt-hours
    private int currentAmount; // in watt-hours
//    private int ElectricCurrent;

    public Battery(int capacity, int currentAmount) {
        this.capacity = capacity;
        this.currentAmount = currentAmount; //capacity;
//        this.ElectricCurrent = elecCurrent;
        this.id = Main.numOfBatteries;
        Main.numOfBatteries += 1;
//        System.out.println("Current of Battery" + this.id + ": " + ElectricCurrent + " Ampere");
    }

    // Synchronized method to charge the battery
    public synchronized void charge(int amount) {
        if (this.currentAmount + amount > capacity) {
            amount = capacity - this.currentAmount;
        }
        this.currentAmount += amount;
        System.out.format("%s charged Battery%d %dWh, Current Charge: %dWh.\n", 
        		Thread.currentThread().getName(), this.id, amount, currentAmount);
    }
    
    public synchronized boolean useEnergy(int amount) {
        if (this.currentAmount >= amount) {// & Main.totalDeviceCurrent <= ElectricCurrent) {
            this.currentAmount -= amount;
            System.out.println(Thread.currentThread().getName() + " used " + amount + "Wh, Remaining Charge: " + currentAmount + "Wh");
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + " attempted to use " + amount + "Wh but insufficient charge!");
            return false;
        }
    }
    
    public int getcurrentAmount() {
        return currentAmount;
    }

    public int getCapacity() {
        return capacity;
    }
    
//    public int getCurrent() {
//        return ElectricCurrent;
//    }
    public int getId() {
    	return id;
    }
}
