package managementsystem;

public class Main {
	static int totalDeviceCurrent = 0;
	static int numOfBatteries = 0;
	public static void main(String[] args) {
		// HI, this is main
		System.out.println("Energy Management System!");
		
		//***********************************************
		// CHARGING BATTERY
		//***********************************************
		// Init energy sources
		EnergySource energySource[] = new EnergySource[] {
				new EnergySource("Solar"), new EnergySource("DC"), new EnergySource("AC")
		};
		
		// Init batteries
		Battery battery[] = new Battery[] {
			new Battery(50,50),
			new Battery(99,99)
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
		chargingBattery(battery, batteryCharging);
		
		//***********************************************
		// BATTERY USAGE
		//***********************************************
		BatteryUsageSimulation usage = new BatteryUsageSimulation();
		usage.Usage();
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
		
		for(int i=0; i<numOfBatteries;i++) {
			System.out.format("Battery%d is fully charged: %dWh.\n", 
					battery[0].getId(), battery[i].getCurrentCharge());
		}
	}
}
