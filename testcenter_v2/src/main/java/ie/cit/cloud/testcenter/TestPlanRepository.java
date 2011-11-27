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

import org.springframework.dao.EmptyResultDataAccessException;

public interface TestPlanRepository {

    /**
     * Returns existing testplan given by its ID
     * 
     * @param id
     *            testplan ID
     * @return testplan for given id, {@link EmptyResultDataAccessException} if no
     *         testplan was found
     */
	TestPlan get(Long id);

    /**
     * Adds new testplan into repository
     * 
     * @param testplan
     */
    void create(TestPlan testplan);

    /**
     * Updates existing testplan. testplan with the same ID as give testplan is updated
     * 
     * @param testplan
     *            new testplan values
     */
    void update(TestPlan testplan);

    /**
     * Deletes testplan item from repository.
     * 
     * @param testplan
     */
    void delete(TestPlan testplan);

    // ================ various find-er methods ================
    /**
     * Returns list of all tests
     * 
     * @return all testplans
     */
    Collection<TestPlan> findAll();

    /**
     * Returns testplan items given by its ID
     * 
     * @param id
     *            testplan ID
     * @return Testplan for given id, null if test was not found
     */
    TestPlan findById(Long id);
    /**
     * Returns testplan items given by its name
     * 
     * @param id
     *            testplan ID
     * @return Testplan for given name, null if testplan was not found
     */
    TestPlan findTestPlanByName(String testplanname);
    

}
