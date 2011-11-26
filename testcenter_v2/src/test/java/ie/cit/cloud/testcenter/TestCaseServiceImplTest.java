package ie.cit.cloud.testcenter;



import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.model.TestPlan;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
    public void testAddNewTestCase() {
    	TestCase testcase = new TestCase();
    	TestPlan testplan = new TestPlan();
	tested.addNewTestCase(testcase,testplan);
	Mockito.verify(repo).create(testcase,testplan);
    }
}
