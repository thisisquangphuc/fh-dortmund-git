package MyExceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MultipleExceptionHandling {
	public boolean handlingMultipleExceptions (String fileName) {
        try {
        	
            /* Attempt to read a file and parse an integer from its content */
        	
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            
            if (!scanner.hasNextLine()) {
            	System.out.println("File is empty: " + fileName);
            	scanner.close();
            	return false;
            }
            
            int number = Integer.parseInt(scanner.nextLine());

            System.out.println("Read number: " + number);
            scanner.close();       
           
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number from file: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("IO Exception occurred while reading the file: " + e.getMessage());
            return false;
        }
        
        return true;
	}
}
