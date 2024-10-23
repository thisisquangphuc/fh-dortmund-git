package managementsystem;

import java.util.concurrent.Semaphore;

public class Main {
	static int totalDeviceCurrent = 0;
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
			new Battery(0, 100, 0),
			new Battery(1, 111, 0)
		};
		
		// Create semaphore corresponding to each battery
		Semaphore key[] = new Semaphore[] { new Semaphore(1), new Semaphore(1) };  
		
		// Init charging pair for each battery and energy source
		BatteryCharging[] batteryCharging = new BatteryCharging[] {
				// battery0 with its corresponding energy sources
				new BatteryCharging(key[0], battery[0], energySource[0], 8),
				new BatteryCharging(key[0], battery[0], energySource[1], 12),
				new BatteryCharging(key[0], battery[0], energySource[2], 25),
				// battery1 with its corresponding energy sources
				new BatteryCharging(key[1], battery[1], energySource[1], 12),
				new BatteryCharging(key[1], battery[1], energySource[2], 25),
		};
		
		// Charge batteries from energy sources
		System.out.println("\nCharging......");
		chargingBattery(battery, batteryCharging);
		System.out.println("\n*********************\n");
		
		//***********************************************
		// BATTERY USAGE
		//***********************************************
			
		// Create and start multiple energy consumers
		Battery myBattery = battery[1];

	    EnergyConsumer device1 = new EnergyConsumer(myBattery, "Device 1", (int) (Math.random() * 10 + 5));
	    EnergyConsumer device2 = new EnergyConsumer(myBattery, "Device 2", (int) (Math.random() * 10 + 5));
	    EnergyConsumer device3 = new EnergyConsumer(myBattery, "Device 3", (int) (Math.random() * 10 + 5));
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

//	    System.out.println("Final Battery Charge: " + battery[1].getcurrentAmount() + "Wh");

	}

	//////////////////
	// Charge batteries from energy sources
	public static void chargingBattery(Battery[] battery, BatteryCharging[] batteryCharging) {
//		boolean allDone = true;
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
		
//		do {
//			allDone = true;
//			for(int i=0; i<batteryCharging.length; i++) {
//				if (batteryCharging[i].isAlive()) {
//					allDone = false;
//					break;
//				}
//			}
//		} while (!allDone);
		
		System.out.println();
		for(int i=0; i<battery.length; i++) {
			System.out.format("Battery%d is fully charged: %dWh.\n", 
					battery[i].getId(), battery[i].getcurrentAmount());
		}
	}
}
