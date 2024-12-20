package managementsystem;
public class Battery {
	private int id;
	private final int capacity; // in watt-hours
    private int currentAmount; // in watt-hours


    public Battery(int id, int capacity, int currentCharge) {
        this.id = id;
    	this.capacity = capacity;
        this.currentAmount = currentCharge; //capacity;
        
    }

    // Synchronized method to charge the battery
    public synchronized void charge(int amount, EnergySourceForBattery chargingEnergy) {
        if (this.currentAmount + amount > capacity) {
            amount = capacity - this.currentAmount;
        }
        this.currentAmount += amount;
        System.out.format("Battery%d is charged %dWh by %s energy, Current Charge: %dWh - %d%%.\n", 
        		this.id, amount, chargingEnergy.getEnergySource().getName(), currentAmount, (int)(100*currentAmount)/capacity);
        if (currentAmount == capacity) {
        	System.out.format("Battery%d is fully charged: %dWh.\n", this.id, currentAmount);
        }
    }
    
    public synchronized boolean useEnergy(int amount) {
        if (this.currentAmount >= amount) {
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
    

    public int getId() {
    	return id;
    }
}
