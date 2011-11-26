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

import org.springframework.dao.EmptyResultDataAccessException;

public interface TestCaseRepository {

    /**
     * Returns existing testcase given by its ID
     * 
     * @param id
     *            test ID
     * @return testcase for given id, {@link EmptyResultDataAccessException} if no
     *         testcase was found
     */
	TestCase get(Long id);

    /**
     * Adds new testcase into repository
     * 
     * @param testcase
     */
    void create(TestCase testcase,TestPlan testplan);

    /**
     * Updates existing testcase. testcase with the same ID as give test is updated
     * 
     * @param testcase
     *            new testcase values
     */
    void update(TestCase testcase);

    /**
     * Deletes testcase item from repository.
     * 
     * @param testcase
     */
    void delete(TestCase testcase);

    // ================ various find-er methods ================
    /**
     * Returns list of all testcases
     * 
     * @return all testcase
     */
    Collection<TestCase> findAll(TestPlan testplan);

    /**
     * Returns testcase items given by its ID
     * 
     * @param id
     *            testcase ID
     * @return testcase for given id, null if testcase was not found
     */
    TestCase findById(Long id);

}