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
		// battery0 with its corresponding energy sources
		EnergySourceForBattery[] energySourceForBattery0 = new EnergySourceForBattery[] {
				new EnergySourceForBattery(key[0], battery[0], energySource[0], 8),  // Solar charge battery0
				new EnergySourceForBattery(key[0], battery[0], energySource[1], 12), // DC charge battery0
				new EnergySourceForBattery(key[0], battery[0], energySource[2], 25)	 // AC charge battery0
		};
		// battery1 with its corresponding energy sources
		EnergySourceForBattery[] energySourceForBattery1 = new EnergySourceForBattery[] {
				new EnergySourceForBattery(key[1], battery[1], energySource[1], 13), // DC charge battery1
				new EnergySourceForBattery(key[1], battery[1], energySource[2], 28)  // AC charge battery1
		};
		
		// Create battery charging management for each battery
		BatteryChargingManagement battery0ChargingMgmt = new BatteryChargingManagement (
				key[0], battery[0], new EnergySourceForBattery[] {
						energySourceForBattery0[0], energySourceForBattery0[1], energySourceForBattery0[2]}
		);
		BatteryChargingManagement battery1ChargingMgmt = new BatteryChargingManagement (
				key[1], battery[1], new EnergySourceForBattery[] {
						energySourceForBattery1[0], energySourceForBattery1[1]}
		);
		
		// Charge batteries from energy sources
		System.out.println("\nCharging......");
		startChargingBattery(battery0ChargingMgmt);
		startChargingBattery(battery1ChargingMgmt);
		
		waitBatteryChargedDone(battery0ChargingMgmt);
		waitBatteryChargedDone(battery1ChargingMgmt);
				
		// wait all threads done
		try {
			for(int i=0; i<energySourceForBattery0.length; i++) {
				energySourceForBattery0[i].join();
			}
			
			for(int i=0; i<energySourceForBattery1.length; i++) {
				energySourceForBattery0[i].join();
			}
			
			battery0ChargingMgmt.join();
			battery1ChargingMgmt.join();
		} catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
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

	    System.out.println("Final Battery Charge: " + battery[1].getcurrentAmount() + "Wh");

	}

	//////////////////
	// Charge battery from energy sources
	public static void startChargingBattery(BatteryChargingManagement batteryChargingMgmt) {
		EnergySourceForBattery chargingEnergy[] = batteryChargingMgmt.getChargingEnergy();
		
		for (int i=0; i<chargingEnergy.length;i++) {
			chargingEnergy[i].start();
		}
	}
	
	// Wait and notify when battery charged done from energy sources
	public static void waitBatteryChargedDone(BatteryChargingManagement batteryChargingMgmt) {
		batteryChargingMgmt.start();
	}
}
