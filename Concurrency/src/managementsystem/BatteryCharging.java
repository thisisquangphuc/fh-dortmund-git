package managementsystem;

public class BatteryCharging extends Thread {
	private Battery battery;
	private EnergySource energyAvail;
	private int chargingRate;
	
	public BatteryCharging (Battery battery, EnergySource energyAvail, int chargingRate) {
		setBattery(battery);
		setEnergyAvail(energyAvail);
		setChargingRate(chargingRate);
	}
	
	@Override
	public void run() {
		System.out.format("%s starts charging the Battery%d.....\n", 
				Thread.currentThread().getName(), battery.getId());
		// Charge the battery
		while (battery.getcurrentAmount() < battery.getCapacity()) {
			battery.charge(chargingRate);
			try {
                Thread.sleep(1000); // Time taken to charge battery 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
		}
		 System.out.format("%s stops charging because the Battery%d has reached MAX capacity %d Wh.\n", 
				 Thread.currentThread().getName(), battery.getId(), battery.getCapacity());
	}
	
	public Battery getBattery() {
		return battery;
	}
	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	public EnergySource getEnergyAvail() {
		return energyAvail;
	}
	public void setEnergyAvail(EnergySource energyAvail) {
		this.energyAvail = energyAvail;
	}
	public int getChargingRate() {
		return chargingRate;
	}
	public void setChargingRate(int chargingRate) {
		this.chargingRate = chargingRate;
	}
}
