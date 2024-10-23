package managementsystem;

import java.util.concurrent.Semaphore;

public class BatteryChargingManagement extends Thread {
	private Battery battery;
	private EnergySourceForBattery[] chargingEnergy;
	private Semaphore key;
	
	public BatteryChargingManagement(Semaphore key, Battery battery, EnergySourceForBattery[] chargingEnergy) {
		setBattery(battery);
		setChargingEnergy(chargingEnergy);
		this.key = key;
	}
	
	@Override
	public void run() {
		boolean done = false;
		// Wait batteries done
		while (true) {
			while (!this.key.tryAcquire());
			done = !(battery.getcurrentAmount()<battery.getCapacity());
			this.key.release();
			if (done == true) break;
		}	
		System.out.format("Battery%d is fully charged: %dWh.\n", 
				battery.getId(), battery.getcurrentAmount());
	}
	
	public Battery getBattery() {
		return battery;
	}
	
	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	
	public EnergySourceForBattery[] getChargingEnergy() {
		return chargingEnergy;
	}
	
	public void setChargingEnergy(EnergySourceForBattery[] chargingEnergy) {
		this.chargingEnergy = chargingEnergy;
	}
}
