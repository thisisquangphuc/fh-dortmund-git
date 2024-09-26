
public class MathCalc {

	public static void main(String[] args) {
	
        double dEarth = 7600.0;
        double dSun = 865000.0;

        double rEarth = dEarth / 2.0;
        double rSun = dSun / 2.0;

        // V = (4/3) * π * r^3
        double vEarth = (4.0 / 3.0) * Math.PI * Math.pow(rEarth, 3);
        double vSun = (4.0 / 3.0) * Math.PI * Math.pow(rSun, 3);

        // ratio
        double volumeRatio = vSun / vEarth;

        // Print the results
        System.out.printf("The volume of the Earth is %.2f cubic miles.\n", vEarth);
        System.out.printf("The volume of the Sun is %.2f cubic miles.\n", vSun);
        System.out.printf("The ratio of the volume of the Sun to the volume of the Earth is %.2f.\n", volumeRatio);
    

	}

}
