/**
 * 
 */
package ie.cit.cloud.testcenter;

import ie.cit.cloud.testcenter.model.TestPlan;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author byrnek1
 *
 */
public class TestPlanServiceImplTest {
	private TestPlanServiceImpl tested;
    private TestPlanRepository repo;    

    @Before
    public void setup() {
    	repo = Mockito.mock(TestPlanRepository.class);
    	tested = new TestPlanServiceImpl();
    	tested.repo = repo;
    }

    @Test
    public void testFindAllTestPlans() {    	
		tested.getAllTestPlans();
		Mockito.verify(repo).findAll();
    }
    
    @Test
    public void testFindTestPlan() {     	
    	TestPlan testplan = new TestPlan();
		tested.getTestPlan(testplan.getId());
		Mockito.verify(repo).findById(testplan.getId());
    }
    
    @Test
    public void testFindTestplanByName() { 
    	TestPlan testplan = new TestPlan();
		tested.getTestPlanByName(testplan.getTestplanName());
		Mockito.verify(repo).findTestPlanByName(testplan.getTestplanName());
    }
    
    @Test
    public void testAddNewTestPlan() {
    	TestPlan testplan = new TestPlan();
    	tested.addNewTestPlan(testplan);
    	Mockito.verify(repo).create(testplan);
    }
    
    @Test
    public void testUpdate() {
    	TestPlan testplan = new TestPlan();
    	tested.update(testplan);
    	Mockito.verify(repo).update(testplan);
    }
    
    @Test
    public void testRemovePlan() {
    	TestPlan testplan = new TestPlan();    	  	
    	tested.remove(testplan.getId());
    	Mockito.verify(repo).delete(repo.get(testplan.getId()));
    }   
    
}
