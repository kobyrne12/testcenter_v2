/**
 * 
 */
package ie.cit.cloud.testcenter;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.TestPlan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Secured("ROLE_USER")
public class TestPlanServiceImpl implements TestPlanService {

    @Autowired
    @Qualifier("hibernateTestPlanRespository")
    TestPlanRepository repo;

    public Collection<TestPlan> getAllTestPlans() {
	return repo.findAll();
    }

    public void addNewTestPlan(TestPlan testplan) {
	repo.create(testplan);
    }

    public TestPlan getTestPlan(Long id) {
	return repo.findById(id);
    }
    public TestPlan getTestPlanByName(String testplanname) {
    	return repo.findTestPlanByName(testplanname);
    }
   
    public void update(TestPlan testplan) {
	repo.update(testplan);
    }

    public void remove(Long id) {
	repo.delete(repo.get(id));
    }

 
    public void updateTestPlanWithId(Long id, TestPlan testplan) {
    	TestPlan oldTestPlan = repo.findById(id);
    	oldTestPlan.setTestplanName(testplan.getTestplanName());
    	oldTestPlan.setTesterName(testplan.getTesterName());
    	oldTestPlan.setTotalTests(testplan.getTotalTests());
    	oldTestPlan.setTotalNotRun(testplan.getTotalNotRun());
    	oldTestPlan.setTotalPassed(testplan.getTotalPassed());
    	oldTestPlan.setTotalFailed(testplan.getTotalFailed());
    	oldTestPlan.setTotalInProgress(testplan.getTotalInProgress());
    	oldTestPlan.setTotalDeferred(testplan.getTotaldeferred());
    	oldTestPlan.setTotalBlocked(testplan.getTotalBlocked());    	
    	repo.update(oldTestPlan);
    }
}