package managementsystem;

public class Equipment {
	private int id;
	private String name;
	private String[] dateCharging;
	private ChargingStation[] stations;
	private EnergySource[] sources;
	
	public Equipment(int id, String name, String[] dateCharging, ChargingStation[] stations, EnergySource[] sources) {
		setId(id);
		setName(name);
		setDateCharging(dateCharging);
		setStations(stations);
		setSources(sources);
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
	
	public String[] getDateCharging() {
		return dateCharging;
	}
	public void setDateCharging(String[] dateCharging) {
		this.dateCharging = dateCharging;
	}
	
	public ChargingStation[] getStations() {
		return stations;
	}
	public void setStations(ChargingStation[] stations) {
		this.stations = stations;
	}
	
	public EnergySource[] getSources() {
		return sources;
	}
	public void setSources(EnergySource[] sources) {
		this.sources = sources;
	}	
}