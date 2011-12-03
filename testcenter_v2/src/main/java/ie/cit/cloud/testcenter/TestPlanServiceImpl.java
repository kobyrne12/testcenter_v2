/**
 * 
 */
package ie.cit.cloud.testcenter;

/**
 * @author byrnek1
 *
 */

import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.model.TestPlan;

import java.util.Collection;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestPlanServiceImpl implements TestPlanService {
	
	private int TempTotalTests;
	private int TempTotalNotRun;
	private int TempTotalPassed;
	private int TempTotalFailed;
	private int TempTotalInProgress;
	private int TempTotalBlocked;
	private int TempTotalDeferred;
	
	
    @Autowired
    @Qualifier("hibernateTestPlanRespository")
    TestPlanRepository repo;
    
    @Autowired
    @Qualifier("hibernateTestCaseRespository")
    TestCaseRepository testcaserepo;    
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Collection<TestPlan> getAllTestPlans() {
	return repo.findAll();
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public TestPlan getTestPlan(Long id) {
	return repo.findById(id);
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public TestPlan getTestPlanByName(String testplanname) {
    	return repo.findTestPlanByName(testplanname);
    }
    
    @Secured("ROLE_ADMIN")
    @Transactional(rollbackFor=ConstraintViolationException.class)   
    public void addNewTestPlan(TestPlan testplan) {
	repo.create(testplan);
    }
    
    @Secured("ROLE_ADMIN")
    public void update(TestPlan testplan) {
	repo.update(testplan);
    }  
    
    @Secured("ROLE_ADMIN")
    public void remove(Long id) {
	repo.delete(repo.get(id));
    }
    
    @Secured("ROLE_ADMIN")    
    @Transactional(rollbackFor=NoResultException.class)
    public boolean updateTestPlan(Long id, TestPlan testplan) {
    	TempTotalTests = 0;
    	TempTotalNotRun = 0;
    	TempTotalPassed = 0;
    	TempTotalFailed = 0;
    	TempTotalInProgress = 0;
    	TempTotalBlocked = 0;
    	TempTotalDeferred = 0;    	
    	
    	try
    	{
    		
	    	Collection<TestCase> testcases = testcaserepo.findAllTestCasesByID(id, testplan);
	    	TempTotalTests = testcases.size();
	    	for (TestCase testcase : testcases)
	    	{
	    		if (testcase.isNotrun())
	    		{
	    			TempTotalNotRun = TempTotalNotRun + 1;
	    		}
	    		if (testcase.isPassed())
	    		{
	    			TempTotalPassed = TempTotalPassed + 1;
	    		}
	    		if (testcase.isFailed())
	    		{
	    			TempTotalFailed = TempTotalFailed + 1;
	    		}
	    		if (testcase.isInprogress())
	    		{
	    			TempTotalInProgress = TempTotalInProgress + 1;
	    		}
	    		if (testcase.isDeferred())
	    		{
	    			TempTotalDeferred = TempTotalDeferred + 1;
	    		}
	    		if (testcase.isBlocked())
	    		{
	    			TempTotalBlocked = TempTotalBlocked + 1;
	    		}
	    	}
	    	//System.out.println("Testcases -- blah -- "+ o.getTestcasename());
	    	
	    	TestPlan oldTestPlan = repo.findById(id);
	    	oldTestPlan.setTestplanName(testplan.getTestplanName());
	    	oldTestPlan.setTesterName(testplan.getTesterName());
	    	oldTestPlan.setTotalTests(TempTotalTests);
	    	oldTestPlan.setTotalNotRun(TempTotalNotRun);
	    	oldTestPlan.setTotalPassed(TempTotalPassed);
	    	oldTestPlan.setTotalFailed(TempTotalFailed);
	    	oldTestPlan.setTotalInProgress(TempTotalInProgress);
	    	oldTestPlan.setTotalDeferred(TempTotalDeferred);
	    	oldTestPlan.setTotalBlocked(TempTotalBlocked);    	
	    	repo.update(oldTestPlan);
	    	return true;
	    }
	    catch(NoResultException NRE)
	    {
	    	return false;
	    }
    }
    @Secured("ROLE_ADMIN")    
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
    @Secured("ROLE_ADMIN")
    public void updateTestPlanNameWithId(Long id, TestPlan testplan, String testplanName) {
    	TestPlan oldTestPlan = repo.findById(id);
    	oldTestPlan.setTestplanName(testplanName);
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