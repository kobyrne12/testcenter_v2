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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Secured("ROLE_USER")
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    @Qualifier("hibernateTestCaseRespository")
    TestCaseRepository repo;

    public Collection<TestCase> getAllTestCases(TestPlan testplan) {
	return repo.findAll(testplan);
    }

    public void addNewTestCase(TestCase testcase,TestPlan testplan) {
	repo.create(testcase,testplan);
    }

    public TestCase getTestCase(Long id) {
	return repo.findById(id);
    }

    public void update(TestCase testcase) {
	repo.update(testcase);
    }

    public void remove(Long id) {
	repo.delete(repo.get(id));
    }

 
 // set Not run 
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
    public void blocked(TestCase testcase) {
	testcase.setNotrun(false);
	testcase.setPassed(false);
	testcase.setFailed(false);
	testcase.setInprogress(false);
	testcase.setDeferred(false);
	testcase.setBlocked(true);
	this.update(testcase);
    }
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
}