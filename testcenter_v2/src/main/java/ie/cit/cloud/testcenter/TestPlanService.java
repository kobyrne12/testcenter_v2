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

/**
 * Peforms business operation on Tests
 */
public interface TestPlanService {

    Collection<TestPlan> getAllTestPlans();

    void addNewTestPlan(TestPlan testplan);

    TestPlan getTestPlan(Long id);
    
    TestPlan getTestPlanByName(String testplanname);
    
    void update(TestPlan testplan);

    void remove(Long id);

    void updateTestPlanWithId(Long id, TestPlan testplan);

}