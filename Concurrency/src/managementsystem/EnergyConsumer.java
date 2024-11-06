package managementsystem;
class EnergyConsumer extends Thread {
    private final Battery battery;
    private final int usageRate; // watts

    public EnergyConsumer(Battery battery, String name, int usageRate) {
        super(name);
        this.battery = battery;
        this.usageRate = usageRate;

        
    }

    @Override
    public void run() {
    	
        while (true) {
            // Attempt to use energy from the battery
            if (!battery.useEnergy(usageRate)) {
                break; // Exit if there's not enough charge
            }
            try {
                Thread.sleep(1000); // Simulate time taken to use energy
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " has stopped running due to insufficient charge.");
    }
}