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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository("hibernateTestPlanRespository")
public class HibernateTestPlanRespository implements TestPlanRepository {

    @PersistenceContext
    private EntityManager em;

    public TestPlan get(Long id) {
	Query query = em.createQuery("from TestPlan where id=:id");
//	Query query = em.createQuery("from TestPlan where user=:user and id=:id");
//	query.setParameter("user", getCurrentUser());
	query.setParameter("id", id);
	return (TestPlan) query.getSingleResult();
    }

    public void create(TestPlan testplan) {
//    	testplan.setUser(getCurrentUser());
	em.persist(testplan);
    }

    public void update(TestPlan testplan) {
	em.merge(testplan);
    }

    public void delete(TestPlan testplan) {
	em.remove(testplan);
    }

    @SuppressWarnings("unchecked")
    public Collection<TestPlan> findAll() {
    Query query = em.createQuery("from TestPlan");	
//	Query query = em.createQuery("from TestPlan where user=:user");
//	query.setParameter("user", getCurrentUser());
 //   query.setParameter("testerName", "john");
	return (List<TestPlan>) query.getResultList();
    }

    public TestPlan findById(Long id) {
	return get(id);
    }

//   private String getCurrentUser() {
//	return SecurityContextHolder.getContext().getAuthentication().getName();
//   }
}
