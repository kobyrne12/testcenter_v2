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

/**
 * Peforms business operation on Tests
 */
public interface TestCaseService {

    Collection<TestCase> getAllTestCasesByID(Long testplanID,TestPlan testplan);
    
    Collection<TestCase> getAllTestCasesByName(Long testplanID,String testcasename);
    
    Collection<TestCase> getAllTestCases();

    void addNewTestCase(TestCase testcase,TestPlan testplan);

    TestCase getTestCase(Long id);

    void update(TestCase testcase);

    void remove(Long id);
    
    void notrun(TestCase testcase);

    void passed(TestCase testcase);
    
    void failed(TestCase testcase);
    
    void inprogress(TestCase testcase);
    
    void deferred(TestCase testcase);
    
    void blocked(TestCase testcase);


    void updateTestCaseWithId(Long id, TestCase testcase);

	void removeAllTestWithID(Long id);
	
	void updateTestCaseDetails(Long testplanID,String testplanName, TestPlan testplan,Long testcaseID, 
			String testcasename, String testcasesummary, String testcasepre, String testcasesteps,
			String testcasepass, String testcaseOS,TestCase testcase);
	

}