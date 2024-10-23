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
		// start charging battery
		for (int i=0; i<chargingEnergy.length; i++) {
			chargingEnergy[i].start();
		}
		// Wait battery done charging
		while (true) {	
			while (!this.key.tryAcquire());
			done = !(battery.getcurrentAmount()<battery.getCapacity());
			this.key.release();
			if (done == true) break;
		}	
		System.out.format("Battery%d DONE charging.\n", battery.getId()); 
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
