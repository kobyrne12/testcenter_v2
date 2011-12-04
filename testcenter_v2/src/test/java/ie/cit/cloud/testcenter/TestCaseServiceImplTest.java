/**
 * 
 */
package ie.cit.cloud.testcenter;

import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.model.TestPlan;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author byrnek1
 *
 */
public class TestCaseServiceImplTest {
	
		private TestCaseServiceImpl tested;
	    private TestCaseRepository repo;

	    @Before
	    public void setup() {
		repo = Mockito.mock(TestCaseRepository.class);
		tested = new TestCaseServiceImpl();
		tested.repo = repo;
	    }

	    @Test
	    public void testFindAllTestCases() {    	
			tested.getAllTestCases();
			Mockito.verify(repo).findAllTestCases();
	    }
	    
	    @Test
	    public void testFindAllTestCasesByID() {     	
	    	TestPlan testplan = new TestPlan();
			tested.getAllTestCasesByID(testplan.getId(), testplan);
			Mockito.verify(repo).findAllTestCasesByID(testplan.getId(), testplan);
	    }
	    
	    @Test
	    public void testFindAllTestCasesByName() {     	
	    	TestPlan testplan = new TestPlan();
	    	TestCase testcase = new TestCase();
			tested.getAllTestCasesByName(testplan.getId(), testcase.getTestcasename());
			Mockito.verify(repo).findAllTestCasesByName(testplan.getId(), testcase.getTestcasename());
	    }  
	    
	    @Test
	    public void testFindTestCases() {     	
	    	Long id = null;
			tested.getTestCase(id);
			Mockito.verify(repo).findById(id);
	    }  
	    
	    @Test
	    public void testAddNewTestCase() {
	    	TestCase testcase = new TestCase();
	    	TestPlan testplan = new TestPlan();
	    	tested.addNewTestCase(testcase,testplan);
	    	Mockito.verify(repo).create(testcase,testplan);
	    }
	    
	    @Test
	    public void testUpdateTestCase() {
	    	TestCase testcase = new TestCase();    	
	    	tested.update(testcase);
	    	Mockito.verify(repo).update(testcase);
	    }
	    
	    @Test
	    public void testRemoveTestCase() {
	    	Long id = null;    	
	    	tested.remove(id);
	    	Mockito.verify(repo).delete(null);
	    }
	    
	    @Test
	    public void testRemoveAllTestCaseWithID() {
	    	Long id = null;      	 
	    	tested.removeAllTestWithID(id);
	    	Mockito.verify(repo).deleteAllTestWithID(id);
	    } 	    
}


