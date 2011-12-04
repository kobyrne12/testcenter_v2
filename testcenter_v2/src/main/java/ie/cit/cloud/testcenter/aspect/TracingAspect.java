/**
 * 
 */
package ie.cit.cloud.testcenter.aspect;

/**
 * @author byrnek1
 *
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracingAspect {

    Log log = LogFactory.getLog(TracingAspect.class);

    /*@Before("execution(* (@org.springframework.stereotype.Repository ie.cit..*).*(..)) && target(repo)")
    public void traceAllMethods(JoinPoint jp, Object repo)
    {
		String clazz = repo.getClass().getName();
		String method = jp.getSignature().getName();
		log.trace("testcenter Before method invoked:" + clazz + "#" + method);
    }    
    
    @After("execution(* (@org.springframework.stereotype.Repository ie.cit..*).*(..)) && target(repo)")
    public void traceAllMethodsAfter(JoinPoint jp, Object repo) 
    {
    	String clazz = repo.getClass().getName();
    	String method = jp.getSignature().getName();
    	log.trace("testcenter method was invoked successfully:" + clazz + "#" + method);
    }*/
    @Before("execution(* find*(*))&& target(repo)")
    public void trackAllFindExtractionsBefore(JoinPoint jp, Object repo)
    {
    	String clazz = repo.getClass().getName();
    	String method = jp.getSignature().getName();
    	log.trace("testcenter - Get DB record(s) method was invoked :" + clazz + "#" + method);;
    }
    
    @After("execution(* find*(*))&& target(repo)")
    public void trackAllFindExtractionsAfter(JoinPoint jp, Object repo)
    {
    	String clazz = repo.getClass().getName();
    	String method = jp.getSignature().getName();
    	log.trace("testcenter - Get DB record(s) method Has completed :" + clazz + "#" + method);;
    }
    @Before("execution(* get*(*))&& target(repo)")
    public void trackAllGetExtractionsBefore(JoinPoint jp, Object repo)
    {
    	String clazz = repo.getClass().getName();
    	String method = jp.getSignature().getName();
    	log.trace("testcenter - Get DB record(s) method was invoked :" + clazz + "#" + method);;
    }
    
    @After("execution(* get*(*))&& target(repo)")
    public void trackAllGetExtractionsAfter(JoinPoint jp, Object repo)
    {
    	String clazz = repo.getClass().getName();
    	String method = jp.getSignature().getName();
    	log.trace("testcenter - Get DB record(s) method Has completed :" + clazz + "#" + method);;
    }
    
    @Before("execution(* update*(*))&& target(repo)")
    public void trackAllUpdatesBefore(JoinPoint jp, Object repo)
    {
    	String clazz = repo.getClass().getName();
    	String method = jp.getSignature().getName();
    	log.trace("testcenter - Update method was invoked :" + clazz + "#" + method);;
    }
    
    @After("execution(* update*(*))&& target(repo)")
    public void trackAllUpdatesAfter(JoinPoint jp, Object repo)
    {
    	String clazz = repo.getClass().getName();
    	String method = jp.getSignature().getName();
    	log.trace("testcenter - Update method Has completed :" + clazz + "#" + method);;
    }
    
    @Before("execution(* add*(*))&& target(repo)")
    public void trackAllInsertsBefore(JoinPoint jp, Object repo)
    {
    	String clazz = repo.getClass().getName();
    	String method = jp.getSignature().getName();
    	log.trace("testcenter - add record to db method was invoked :" + clazz + "#" + method);;
    }
    
    @After("execution(* add*(*))&& target(repo)")
    public void trackAllInsertsAfter(JoinPoint jp, Object repo)
    {
    	String clazz = repo.getClass().getName();
    	String method = jp.getSignature().getName();
    	log.trace("testcenter - add record to db method Has completed :" + clazz + "#" + method);;
    }
}