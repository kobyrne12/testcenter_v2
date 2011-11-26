package ie.cit.cloud.testcenter;



import ie.cit.cloud.testcenter.model.TestPlan;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
    public void testAddNewTestPLan() {
    	TestPlan testplan = new TestPlan();
	tested.addNewTestPlan(testplan);
	Mockito.verify(repo).create(testplan);
    }
}
