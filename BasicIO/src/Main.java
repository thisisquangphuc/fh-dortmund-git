import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello, This is main class!");
		//***************************************************************
		MultipleExceptionHandling demoA = new MultipleExceptionHandling();

        // Handling multiple exceptions example
        System.out.println("\n**** Handling multiple exceptions result");
        demoA.handlingMultipleExceptions("logs/log3.txt");

        //***************************************************************
        ReThrowingExceptions demoB = new ReThrowingExceptions();
        System.out.println("\n**** Re-throwing exception result");
        // Re-throwing exception example
        try {
            demoB.reThrowingExceptions("NotExistedFile.txt");
        } catch (IOException e) {
            System.out.println("Error: Exception caught in main: " + e.getMessage());
        }
        
	}

}
