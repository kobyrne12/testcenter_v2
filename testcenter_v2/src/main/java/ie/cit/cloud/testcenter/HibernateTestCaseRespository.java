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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository("hibernateTestCaseRespository")
public class HibernateTestCaseRespository implements TestCaseRepository {
	
//	private TestPlanService testplanService;
	
    @PersistenceContext
    private EntityManager em;

    public TestCase get(Long id) {
	Query query = em.createQuery("from TestCase where user=:user and id=:id");
	query.setParameter("user", getCurrentUser());
	query.setParameter("id", id);
	return (TestCase) query.getSingleResult();
    }

    public void create(TestCase testcase,TestPlan testplan) {
    	testcase.setUser(getCurrentUser());
    	testcase.setTestplanName(testplan.getTestplanName());
	em.persist(testcase);
    }

    public void update(TestCase testcase) {
	em.merge(testcase);
    }

    public void delete(TestCase testcase) {
	em.remove(testcase);
    }
    
    public void deleteAllTestWithID(Long id) {
    	Query query = em.createQuery("delete from TestCase where testPlanID=:testPlanID");      	
    	query.setParameter("testPlanID", id);
    	query.executeUpdate();		
    }
    @SuppressWarnings("unchecked")
    public Collection<TestCase> findAllTestCasesByID(Long testplanID,TestPlan testplan) {
    	Query query = em.createQuery("from TestCase where testplanID=:testplanID"); 
    	//TestPlan testplan = testplanService.getTestPlan(testplan);
    	query.setParameter("testplanID", testplanID);
    	return (List<TestCase>) query.getResultList();
    }
    @SuppressWarnings("unchecked")
    public Collection<TestCase> findAllTestCasesByName(Long testplanID,String testcasename) {
    	Query query = em.createQuery("from TestCase where testplanID=:testplanID and testcasename=:testcasename");     	
    	query.setParameter("testplanID", testplanID);
    	query.setParameter("testcasename", testcasename);    	
    	return (List<TestCase>) query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public Collection<TestCase> findAllTestCases() {
	Query query = em.createQuery("from TestCase where user=:user");
	query.setParameter("user", getCurrentUser());
	return (List<TestCase>) query.getResultList();
    }

    public TestCase findById(Long id) {
	return get(id);
    }
   
    private String getCurrentUser() {
	return SecurityContextHolder.getContext().getAuthentication().getName();
    }
	

}
