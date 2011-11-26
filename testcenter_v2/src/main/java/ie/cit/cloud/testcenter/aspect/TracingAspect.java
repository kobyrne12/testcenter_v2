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
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracingAspect {

    Log log = LogFactory.getLog(TracingAspect.class);

    @Before("execution(* (@org.springframework.stereotype.Repository ie.cit..*).*(..)) && target(repo)")
    public void trace(JoinPoint jp, Object repo) {
	String clazz = repo.getClass().getName();
	String method = jp.getSignature().getName();
	log.trace("method invoked:" + clazz + "#" + method);
    }
}