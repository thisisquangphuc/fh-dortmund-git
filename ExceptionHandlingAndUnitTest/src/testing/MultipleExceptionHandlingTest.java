package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import MyExceptions.MultipleExceptionHandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

class MultipleExceptionHandlingTest {

	@Test
    public void testFileNotFound() {
        MultipleExceptionHandling meh = new MultipleExceptionHandling();

        boolean result = meh.handlingMultipleExceptions("nonexistent.txt");
        assertFalse(result, "The method should return false for a non-existent file.");
    }

    @Test
    public void testInvalidNumberFormat() throws IOException {
        MultipleExceptionHandling meh = new MultipleExceptionHandling();
        File tempFile = File.createTempFile("test", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("invalid_number");
        }
        
        boolean result = meh.handlingMultipleExceptions(tempFile.getAbsolutePath());
        assertFalse(result, "The method should return false for a file with invalid number format.");
        tempFile.deleteOnExit(); // Cleanup
    }

    @Test
    public void testValidNumberFormat() throws IOException {
        MultipleExceptionHandling meh = new MultipleExceptionHandling();
        File tempFile = File.createTempFile("test", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("42");
        }
        
        boolean result = meh.handlingMultipleExceptions(tempFile.getAbsolutePath());
        assertTrue(result, "The method should return true for a file with a valid integer.");
        tempFile.deleteOnExit(); // Cleanup
    }

    @Test
    public void testEmptyFile() throws IOException {
        MultipleExceptionHandling meh = new MultipleExceptionHandling();
        File tempFile = File.createTempFile("emptyTest", ".txt");

        boolean result = meh.handlingMultipleExceptions(tempFile.getAbsolutePath());
        assertFalse(result, "The method should return false for an empty file as no number can be parsed.");
        tempFile.deleteOnExit(); // Cleanup
    }

    @Test
    public void testIOException() {
        MultipleExceptionHandling meh = new MultipleExceptionHandling();
        // Assuming "C:/protectedPath.txt" has restricted access
        boolean result = meh.handlingMultipleExceptions("C:/protectedPath.txt");
        assertFalse(result, "The method should return false when an IOException occurs.");
    }
}
