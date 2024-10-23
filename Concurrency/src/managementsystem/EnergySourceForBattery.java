package managementsystem;

import java.util.concurrent.Semaphore;

public class EnergySourceForBattery extends Thread {
	private Battery battery;
	private EnergySource energySource;
	private int chargingRate;
	private Semaphore key;
	
	public EnergySourceForBattery(Semaphore key, Battery battery, EnergySource energySource, int chargingRate) {
		setEnergySource(energySource);
		setBattery(battery);
		setChargingRate(chargingRate);
		this.key = key;
	}

	@Override
	public void run() {
		// Charge the battery
		while (true) {
			while(!this.key.tryAcquire());
			try {
				if (battery.getcurrentAmount() >= battery.getCapacity()) break;
				battery.charge(chargingRate, this);
			} finally {
				this.key.release();
			}
		}
	}

	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	
	public EnergySource getEnergySource() {
		return energySource;
	}

	public void setEnergySource(EnergySource energySource) {
		this.energySource = energySource;
	}

	public int getChargingRate() {
		return chargingRate;
	}
	
	public void setChargingRate(int chargingRate) {
		this.chargingRate = chargingRate;
	}
}