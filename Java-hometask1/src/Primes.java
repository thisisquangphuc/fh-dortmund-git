
public class Primes {

	public static void main(String[] args) {
        int nValues = 50;

        loopHere:
        for (int i = 2; i <= nValues; i++) {
            // Only check up to the square root of 'i'
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    // Skip to the next number
                    continue loopHere;
                }
            }
            // Printout 
            System.out.println(i);
        }
    }

}
