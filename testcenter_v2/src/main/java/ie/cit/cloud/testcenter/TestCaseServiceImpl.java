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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    @Qualifier("hibernateTestCaseRespository")
    TestCaseRepository repo;
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Collection<TestCase> getAllTestCases() {
    	return repo.findAllTestCases();
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Collection<TestCase> getAllTestCasesByID(Long testplanID,TestPlan testplan) {
    	return repo.findAllTestCasesByID(testplanID,testplan);
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public Collection<TestCase> getAllTestCasesByName(Long testplanID,String testcasename){    	
    	return repo.findAllTestCasesByName(testplanID,testcasename);
    }
    
    @Transactional(rollbackFor=NoResultException.class,readOnly=true)
    public TestCase getTestCase(Long id) {
	return repo.findById(id);
    }
    
    @Secured("ROLE_ADMIN")
    @Transactional(rollbackFor=ConstraintViolationException.class)   
    public void addNewTestCase(TestCase testcase,TestPlan testplan) {
	repo.create(testcase,testplan);
    }   
    
    @Secured("ROLE_ADMIN")    
    public void update(TestCase testcase) {
	repo.update(testcase);
    }
    
    @Secured("ROLE_ADMIN")   
    public void remove(Long id) {
	repo.delete(repo.get(id));
    }
    
    @Secured("ROLE_ADMIN")       
    public void removeAllTestWithID(Long id) {
    	repo.deleteAllTestWithID(id);
    }
 
 // set Not run 
    @Secured("ROLE_ADMIN")   
    public void notrun(TestCase testcase) {
	testcase.setNotrun(true);
	testcase.setPassed(false);
	testcase.setFailed(false);
	testcase.setInprogress(false);
	testcase.setDeferred(false);
	testcase.setBlocked(false);
	this.update(testcase);
    }
    // set Passed 
    @Secured("ROLE_ADMIN")
    public void passed(TestCase testcase) {
	testcase.setNotrun(false);
	testcase.setPassed(true);
	testcase.setFailed(false);
	testcase.setInprogress(false);
	testcase.setDeferred(false);
	testcase.setBlocked(false);
	this.update(testcase);
    }
    // set Failed
    @Secured("ROLE_ADMIN")
    public void failed(TestCase testcase) {
	testcase.setNotrun(false);
	testcase.setPassed(false);
	testcase.setFailed(true);
	testcase.setInprogress(false);
	testcase.setDeferred(false);
	testcase.setBlocked(false);
	this.update(testcase);
    }
    // set Inprogress 
    @Secured("ROLE_ADMIN")
    public void inprogress(TestCase testcase) {
	testcase.setNotrun(false);
	testcase.setPassed(false);
	testcase.setFailed(false);
	testcase.setInprogress(true);
	testcase.setDeferred(false);
	testcase.setBlocked(false);
	this.update(testcase);
    }
    // set Deferred
    @Secured("ROLE_ADMIN")
    public void deferred(TestCase testcase) {
	testcase.setNotrun(false);
	testcase.setPassed(false);
	testcase.setFailed(false);
	testcase.setInprogress(false);
	testcase.setDeferred(true);
	testcase.setBlocked(false);
	this.update(testcase);
    }
    // set Blocked
    @Secured("ROLE_ADMIN")
    public void blocked(TestCase testcase) {
	testcase.setNotrun(false);
	testcase.setPassed(false);
	testcase.setFailed(false);
	testcase.setInprogress(false);
	testcase.setDeferred(false);
	testcase.setBlocked(true);
	this.update(testcase);
    }
    
    @Secured("ROLE_ADMIN")
    public void updateTestCaseWithId(Long id, TestCase testcase) {
    	TestCase oldTestCase = repo.findById(id);
    //	oldTestCase.setTestplanID(testcase.getTestplanID());
    	oldTestCase.setTestcasename(testcase.getTestcasename());
    	oldTestCase.setTestcasesummary(testcase.getTestcasesummary());
    	oldTestCase.setTestcasepre(testcase.getTestcasepre());
    	oldTestCase.setTestcasesteps(testcase.getTestcasesteps());
    	oldTestCase.setTestcasepass(testcase.getTestcasepass());
    	oldTestCase.setTestcaseOS(testcase.getTestcaseOS());
    	oldTestCase.setNotrun(testcase.isNotrun());
    	oldTestCase.setPassed(testcase.isPassed());
    	oldTestCase.setFailed(testcase.isFailed());
    	oldTestCase.setInprogress(testcase.isInprogress());
    	oldTestCase.setDeferred(testcase.isDeferred());
    	oldTestCase.setBlocked(testcase.isBlocked());	
    	repo.update(oldTestCase);
    }
    
    @Secured("ROLE_ADMIN")
	public void updateTestCaseDetails(Long testplanID, String testplanName,
			TestPlan testplan, Long testcaseID, String testcasename, String testcasesummary,
			String testcasepre, String testcasesteps,
			String testcasepass, String testcaseOS, TestCase testcase) {
		
		TestCase oldTestCase = repo.findById(testcaseID);
	    oldTestCase.setTestPlanID(testplanID);
	    oldTestCase.setTestPlan(testplan);
	    oldTestCase.setTestplanName(testplanName);
	    oldTestCase.setTestcasename(testcasename);
	    oldTestCase.setTestcasesummary(testcasesummary);
	    oldTestCase.setTestcasepre(testcasepre);
	    oldTestCase.setTestcasesteps(testcasesteps);
	    oldTestCase.setTestcasepass(testcasepass);
	    oldTestCase.setTestcaseOS(testcaseOS);
	    oldTestCase.setNotrun(testcase.isNotrun());
	    oldTestCase.setPassed(testcase.isPassed());
	    oldTestCase.setFailed(testcase.isFailed());
	    oldTestCase.setInprogress(testcase.isInprogress());
	    oldTestCase.setDeferred(testcase.isDeferred());
	    oldTestCase.setBlocked(testcase.isBlocked());	
	    repo.update(oldTestCase);
		
	}
}