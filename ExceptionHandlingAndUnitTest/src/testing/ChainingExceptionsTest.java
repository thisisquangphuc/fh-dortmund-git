package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import EnergySystem.ChargingStation;
import EnergySystem.EnergySource;
import MyExceptions.ChainingExceptions;

class ChainingExceptionsTest {
	
	@Test 
	public void testCreateExceptionObj() {
		ChainingExceptions chainE = new ChainingExceptions();
		assertNotNull(chainE, "ChainingExceptions Object did not be created.");
		System.out.println("[TEST] ChainingExceptions Object is created successfully.");
	}
	
	@Test 
	public void testExceptionInitMess() {
		ChainingExceptions chainE = new ChainingExceptions();
		boolean messNull = chainE.messIsNull();
		assertTrue(messNull, "Message should not be set before calling chainingExeptions() method.");
		assertNull(chainE.getEx(), "Exception should not be created before calling chainingExeptions() method.");
		System.out.println("[TEST] Message and Exceptions are null before calling chainingExeptions() method.");
	}
		
	@Test 
	public void testChainingExceptionsNotOccur() {
		ChainingExceptions chainE = new ChainingExceptions();
		
		// Create energy source data 
		EnergySource energySource[] = new EnergySource[] {
				new EnergySource("Petrol"), new EnergySource("DC"), new EnergySource("AC")
				};
		// Create charging stations data 
		ChargingStation stations[] = new ChargingStation[] {};
		try {
			stations = new ChargingStation[] {
				new ChargingStation(0, "Essen", new EnergySource[]{energySource[0], energySource[2]})
			};
		} catch(ArrayIndexOutOfBoundsException e) {
			chainE.chainingExceptions(e, "Array out of bound!");
		}
		assertEquals(null, chainE.getMess());
		assertNull(chainE.getEx(), "No Exception is expected to throw.");
		System.out.println("[TEST] No Exception is thrown.");
	}
	
	@Test
	public void testMessChainingExceptionsOccur() {
		ChainingExceptions chainE = new ChainingExceptions();
		
		// Create energy source data 
		EnergySource energySource[] = new EnergySource[] {
				new EnergySource("Petrol"), new EnergySource("DC"), new EnergySource("AC")
				};
		// Create charging stations data 
		ChargingStation stations[] = new ChargingStation[] {};
		try {
			stations = new ChargingStation[] {
				new ChargingStation(0, "Essen", new EnergySource[]{energySource[0], energySource[2]}),
				new ChargingStation(1, "Dortmund", new EnergySource[]{energySource[1],energySource[3]})
			};
		} catch(ArrayIndexOutOfBoundsException actEx) {
			chainE.chainingExceptions(actEx, "Array out of bound!");
		}
		assertEquals("Index 3 out of bounds for length 3", chainE.getMess());
		System.out.println("[TEST] Exception is thrown.");
	}
	
	@Test
	public void testExChainingExceptionsOccur() { 
		ChainingExceptions chainE = new ChainingExceptions();
		ArrayIndexOutOfBoundsException refEx = new ArrayIndexOutOfBoundsException();
		
		// Create energy source data 
		EnergySource energySource[] = new EnergySource[] {
				new EnergySource("Petrol"), new EnergySource("DC"), new EnergySource("AC")
				};
		// Create charging stations data 
		ChargingStation stations[] = new ChargingStation[] {};
		try {
			stations = new ChargingStation[] {
				new ChargingStation(0, "Essen", new EnergySource[]{energySource[0], energySource[2]}),
				new ChargingStation(1, "Dortmund", new EnergySource[]{energySource[1],energySource[3]})
			};
		} catch(ArrayIndexOutOfBoundsException actEx) {
			chainE.chainingExceptions(actEx, "Array out of bound!");
		}
		assertSame(chainE.getEx().getClass(), refEx.getClass());
		System.out.println("[TEST] Exception type of class matched.");
	}
}
