package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import MyExceptions .ReThrowingExceptions;

class ReThrowingExceptionsTest {

	private String testPath = "logs/log3.txt"; //File has content
	@Test
    public void testObjectCreation() {
        ReThrowingExceptions rte = new ReThrowingExceptions();
        assertNotNull(rte, "Object should be created");
        System.out.println("ReThrowingExceptions object created successfully.");
    }

    @Test
    public void testcheckEmptyFile_NotEmptyFile() throws IOException {
        ReThrowingExceptions rte = new ReThrowingExceptions();
    	boolean value = rte.checkEmptyFile(testPath);
    	assertFalse(value, "Returned value should not be null");
    }

    @Test
    public void testisNumber_NotANumber() {
    	/* Content of log3 is not a number => expected fail */
        ReThrowingExceptions rte = new ReThrowingExceptions();
        try {
        	rte.reThrowingExceptions(testPath);
        	boolean condition = rte.isNumber();
        	assertFalse(condition, "Content is a number");
        } catch (Exception e) {
        	System.out.println("testisNumber: Exception thrown.");
        	fail("Data invalid.");
        }
    }

    @Test
    public void testEquality() {
        ReThrowingExceptions rte = new ReThrowingExceptions();
        try {
        	rte.reThrowingExceptions(testPath);
        	System.out.println("testEquality: Exception thrown." + rte.getResult());
        	assertEquals("{ station C, equipment:Car3, energy_source:AC, date: 9.10.2024 }", rte.getResult(), "The result should match the expected value");
        } catch (Exception e) {
        	System.out.println("testEquality: Exception thrown.");
        	fail("Data invalid.");
        }
        	
    }

    @Test
    public void testDifferentObjects() {
        ReThrowingExceptions rte1 = new ReThrowingExceptions();
        ReThrowingExceptions rte2 = new ReThrowingExceptions();
        assertNotSame(rte1, rte2, "These two objects should not be the same reference");
        System.out.println("Different instances created successfully.");
    }

}
