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
	    public void testFindTestCase() {     	
	    	TestCase testcase = new TestCase();
			tested.getTestCase(testcase.getId());
			Mockito.verify(repo).findById(testcase.getId());
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
	    	TestCase testcase = new TestCase();    	
	    	tested.remove(testcase.getId());
	    	Mockito.verify(repo).delete(null);
	    }
	    
	    @Test
	    public void testRemoveAllTestCaseWithID() {
	    	TestPlan testplan = new TestPlan();    	 
	    	tested.removeAllTestWithID(testplan.getId());
	    	Mockito.verify(repo).deleteAllTestWithID(testplan.getId());
	    } 	    
}


