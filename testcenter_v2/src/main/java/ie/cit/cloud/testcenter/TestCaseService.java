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

    Collection<TestCase> getAllTestCases(TestPlan testplan);

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

}