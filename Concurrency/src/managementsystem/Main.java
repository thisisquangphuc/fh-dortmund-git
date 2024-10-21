package managementsystem;

public class Main {

	static int numOfBatteries = 0;
	public static void main(String[] args) {
		// HI, this is main
		System.out.println("\n*********************\n");
		System.out.println("Energy Management System!");
		System.out.println("\n*********************\n");
		
		//***********************************************
		// CHARGING BATTERY
		//***********************************************
		// Init energy sources
		EnergySource energySource[] = new EnergySource[] {
				new EnergySource("Solar"), new EnergySource("DC"), new EnergySource("AC")
		};
		
		// Init batteries
		Battery battery[] = new Battery[] {
			new Battery(50,20),
			new Battery(100,20)
		};
		numOfBatteries = battery.length;
		
		// Init battery charging for each battery and energy source
		BatteryCharging[] batteryCharging = new BatteryCharging[] {
				new BatteryCharging(battery[0], energySource[0], 8),
				new BatteryCharging(battery[0], energySource[1], 12),
				new BatteryCharging(battery[0], energySource[2], 25),
				new BatteryCharging(battery[1], energySource[1], 12),
				new BatteryCharging(battery[1], energySource[2], 25),
		};
		// charge batteries from energy sources
		System.out.println("\nCharging......");
		chargingBattery(battery, batteryCharging);
		System.out.println("\n*********************\n");
		
		//***********************************************
		// BATTERY USAGE
		//***********************************************
//		BatteryUsageSimulation usage = new BatteryUsageSimulation();
//		usage.Usage(battery[1]);
		
		// Create and start multiple energy consumers

	    EnergyConsumer device1 = new EnergyConsumer(battery[1], "Device 1", (int) (Math.random() * 10 + 5));
	    EnergyConsumer device2 = new EnergyConsumer(battery[1], "Device 2", (int) (Math.random() * 10 + 5));
	    EnergyConsumer device3 = new EnergyConsumer(battery[1], "Device 3", (int) (Math.random() * 10 + 5));
	    //System.out.println("Total Current from Devices: " + Main.totalDeviceCurrent + " Ampere");
	    
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

	    System.out.println("Final Battery Charge: " + battery[1].getcurrentAmount() + "Wh");

	}

	//////////////////
	// Charge batteries from energy sources
	public static void chargingBattery(Battery[] battery, BatteryCharging[] batteryCharging) {
		// start thread to charge battery
		for(int i=0; i<batteryCharging.length; i++) {
			batteryCharging[i].start();
		}
		// wait all threads done
		try {
			for(int i=0; i<batteryCharging.length; i++) {
				batteryCharging[i].join();
			}
		} catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
		
		System.out.println("\nCharging DONE......");
		for(int i=0; i<numOfBatteries;i++) {
			System.out.format("Battery%d is fully charged: %dWh.\n", 
					battery[0].getId(), battery[i].getcurrentAmount());
		}
	}
}
