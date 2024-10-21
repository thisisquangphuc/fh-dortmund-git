package managementsystem;

public class Main {
	static int totalDeviceCurrent = 0;
	public static void main(String[] args) {
		// HI, this is main
		System.out.println("Energy Managerment System!");
		BatteryUsageSimulation usage = new BatteryUsageSimulation();
		usage.Usage();
	}

}
