package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import MyExceptions.MultipleExceptionHandling;

class MultipleExceptionHandlingTest {

	@Test
    public void testFileNotFound() {
        MultipleExceptionHandling meh = new MultipleExceptionHandling();
        // Redirecting output to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        meh.handlingMultipleExceptions("nonexistent.txt");

        String output = outputStream.toString().trim();
        assertEquals("File not found: nonexistent.txt", output);
        
        // Resetting output stream
        System.setOut(originalOut);
        System.out.println("Test for file not found completed.");
    }

}
