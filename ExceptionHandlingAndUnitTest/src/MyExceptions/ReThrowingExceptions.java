package MyExceptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReThrowingExceptions {
	// Method demonstrating re-throwing exceptions
	private String content;
	
    public void reThrowingExceptions(String filePath) throws IOException {
        try {
            /* Attempt to read all lines from a file */
        	
            Path path = Paths.get(filePath);
            content = Files.readString(path);
            System.out.println("File content: " + content);
            

        } catch (IOException e) {
            System.out.println("Error: An exception found while reading the file: " + filePath);
            // Log the exception (could be to a file or logging framework)
            logException(e);

            // Re-throw the exception for the caller to handle
            throw e;
        }
    }
    
    public String getResult() {
        return content;
    }

    public boolean isNumber() {
        try {
            Integer.parseInt(content);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean checkEmptyFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false; // File does not exist
        }
        return file.length() == 0; // Returns true if file is empty
    }
    
    // Example method to simulate logging
    private void logException(Exception e) {
        System.out.println("Logging exception: " + e.toString());
    }
}
