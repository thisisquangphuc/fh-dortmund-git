import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MultipleExceptionHandling {
	public void handlingMultipleExceptions (String fileName) {
        try {
            // Attempt to read a file and parse an integer from its content
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int number = Integer.parseInt(scanner.nextLine());

            System.out.println("Read number: " + number);
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number from file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception occurred while reading the file: " + e.getMessage());
        }
    }
	
}
