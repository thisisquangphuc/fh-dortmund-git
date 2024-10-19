package testing;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.*;
import MyExceptions.ResourceManagement;
class ResourceMngTest {
	
	ResourceManagement resourcemanagement;
	@BeforeEach                                         
    void setUp() {
		resourcemanagement = new ResourceManagement();
    }
	
	@Test                                               
    @DisplayName("Test Resource Management testcheckContent_TestFail")   
    void testcheckContent_TestFail() throws IOException {
		int a = resourcemanagement.checkContent("test_files", "test2");
		System.out.println("Check fail : "+ a);
//		PrintStream originalOut = System.out;
//		System.setOut(originalOut);
        assertEquals(0, a, "Test fail!");                
    }
	
	@Test                                               
    @DisplayName("Test Resource Management fail - testcheckContent_TestfileEmpty")   
    void testcheckContent_TestfileEmpty() throws IOException {
		int a = resourcemanagement.checkContent("test_files", "test3");
		System.out.println("Check file emp : "+ a);
		assertEquals(1, a, "Test fail!");
                 
    }
	
	@Test                                               
    @DisplayName("Test Resource Management Successfully testcheckContent_TestSuccess")   
    void testcheckContent_TestSuccess() throws IOException {
		int a = resourcemanagement.checkContent("test_files", "test");
		System.out.println("Check succ : "+ a);
		assertEquals(2, a, "Test successful!");
                 
    }
	
	@Test
	@DisplayName("Test Resource Management testcheckExist_testFileNotExist")
	public void testcheckExist_testFileNotExist() throws IOException {	
		int a = resourcemanagement.checkExist("test_files", "test4");
		System.out.println("Check file not exist: "+ a);
		assertEquals(3, a, "File not exist");
                   
    }
	
	@Test                                               
    @DisplayName("Test Resource Management testcheckExist_Folder Exist")   
    void testcheckExist_FolderExist() throws IOException {
        int a = resourcemanagement.checkExist("test_empty_folder", "test3");
        System.out.println("Check folder empty: "+ a);
        assertEquals(4, a, "Folder is not empty or existed");                
    }


//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
