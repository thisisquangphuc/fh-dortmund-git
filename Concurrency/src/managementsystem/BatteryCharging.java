package managementsystem;

import java.util.concurrent.Semaphore;

public class BatteryCharging extends Thread {
	private Battery battery;
	private EnergySource chargingEnergy;
	private int chargingRate;
	private Semaphore key;
	
	public BatteryCharging (Semaphore key, Battery battery, EnergySource energyAvail, int chargingRate) {
		setBattery(battery);
		setChargingEnergy(energyAvail);
		setChargingRate(chargingRate);
		this.key = key;
	}
	
	@Override
	public void run() {
		System.out.format("Start charging Battery%d by %s energy.....\n", 
				battery.getId(), chargingEnergy.getName());
		// Charge the battery
		while (true) {
			while(!this.key.tryAcquire());
			try {
				if (battery.getcurrentAmount() >= battery.getCapacity()) break;
				battery.charge(chargingRate, chargingEnergy);
			} finally {
				this.key.release();
			}
		}
		System.out.format("%s can not continue charging Battery%d because of MAX capacity %d Wh.\n", 
				chargingEnergy.getName(), battery.getId(), battery.getCapacity());
	}
	
	public Battery getBattery() {
		return battery;
	}
	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	public EnergySource getChargingEnergy() {
		return chargingEnergy;
	}
	public void setChargingEnergy(EnergySource energyAvail) {
		this.chargingEnergy = energyAvail;
	}
	public int getChargingRate() {
		return chargingRate;
	}
	public void setChargingRate(int chargingRate) {
		this.chargingRate = chargingRate;
	}
}
