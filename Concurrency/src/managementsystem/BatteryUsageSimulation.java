package managementsystem;
class EnergyConsumer extends Thread {
    private final Battery battery;
    private final int usageRate; // watts

    public EnergyConsumer(Battery battery, String name, int usageRate, int DeviceCurrent) {
        super(name);
        this.battery = battery;
        this.usageRate = usageRate;
        Main.totalDeviceCurrent = Main.totalDeviceCurrent + DeviceCurrent;
        
    }

    @Override
    public void run() {
    	//System.out.println("Total Current from Devices: " + Main.totalDeviceCurrent);
        while (true) {
            // Attempt to use energy from the battery
            if (!battery.useEnergy(usageRate) || Main.totalDeviceCurrent > battery.getCurrent()) {
                break; // Exit if there's not enough charge
            }
            try {
                Thread.sleep(1000); // Simulate time taken to use energy
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " has stopped running due to insufficient charge.");
    }
}

public class BatteryUsageSimulation {
	public void Usage() {
		Battery battery = new Battery(100, 100); // Create a battery with a capacity of 100Wh

	    // Create and start multiple energy consumers

	    EnergyConsumer device1 = new EnergyConsumer(battery, "Device 1", (int) (Math.random() * 10 + 5), 20);
	    EnergyConsumer device2 = new EnergyConsumer(battery, "Device 2", (int) (Math.random() * 10 + 5), 30);
	    EnergyConsumer device3 = new EnergyConsumer(battery, "Device 3", (int) (Math.random() * 10 + 5), 50);
	    System.out.println("Total Current from Devices: " + Main.totalDeviceCurrent + " Ampere");
	    
	    device1.start();
	    device2.start();
	    device3.start();

	    // Wait for all threads to finish
	    try {
	        device1.join();
	        device2.join();
	        device3.join();
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }

	    System.out.println("Final Battery Charge: " + battery.getCurrentCharge() + "Wh");
	}
}