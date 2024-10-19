package EnergySystem;

public class ChargingStation {
	private int id;
	private String name;
	private EnergySource[] energyAvail;
	
	public ChargingStation(int id, String name, EnergySource[] energyAvail) {
		setId(id);
		setName(name);
		setEnergyAvail(energyAvail);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public EnergySource[] getEnergyAvail() {
		return energyAvail;
	}
	public void setEnergyAvail(EnergySource[] energyAvail) {
		this.energyAvail = energyAvail;
	}
}