package testing;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({MultipleExceptionHandlingTest.class, ReThrowingExceptionsTest.class, ChainingExceptionsTest.class} )
public class AllTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
