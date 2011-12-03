/**
 * 
 */
package ie.cit.cloud.testcenter.aspect;

/**
 * @author byrnek1
 *
 */

import javax.persistence.NoResultException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracingAspect {

    Log log = LogFactory.getLog(TracingAspect.class);

    @Before("execution(* (@org.springframework.stereotype.Repository ie.cit..*).*(..)) && target(repo)")
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
    }
    
    @After("execution(void update*(*))&& target(repo)")
    public void trackAllUpdates(JoinPoint jp, Object repo)
    {
    	String clazz = repo.getClass().getName();
    	String method = jp.getSignature().getName();
    	log.trace("testcenter Update method was invoked successfully:" + clazz + "#" + method);;
    }
}