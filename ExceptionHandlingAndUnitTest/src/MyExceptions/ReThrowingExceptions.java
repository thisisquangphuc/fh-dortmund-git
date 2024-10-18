package MyExceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReThrowingExceptions {
	// Method demonstrating re-throwing exceptions
    public void reThrowingExceptions(String filePath) throws IOException {
        try {
            // Attempt to read all lines from a file
            Path path = Paths.get(filePath);
            String content = Files.readString(path);
            System.out.println("File content: " + content);

        } catch (IOException e) {
            System.out.println("Error: An exception found while reading the file: " + filePath);
            // Log the exception (could be to a file or logging framework)
            logException(e);

            // Re-throw the exception for the caller to handle
            throw e;
        }
    }

    // Example method to simulate logging
    private void logException(Exception e) {
        System.out.println("Logging exception: " + e.toString());
    }
}
