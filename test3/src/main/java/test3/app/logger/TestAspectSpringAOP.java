package test3.app.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// SpringAOPではなくAspectJを使う
// resourcesの下にMETA-INF/aop.xmlを置く
// spring-servlet.xmlに<context:load-time-weaver aspectj-weaving="on" />を追加
// tomcatの起動VMオプションに -javaagent:"D:\java\.m2\repository\org\aspectj\aspectjweaver\1.8.7\aspectjweaver-1.8.7.jar"

@Aspect
public class TestAspectSpringAOP {

	@Around("execution(public * test3..AsyncController.*(..))")
	public Object around1(ProceedingJoinPoint point) throws Throwable {

		return impl(point);
	}

	@Around("execution(public * test3..TestController1.*(..))")
	public Object around2(ProceedingJoinPoint point) throws Throwable {

		return impl(point);
	}

	private Object impl(ProceedingJoinPoint point) throws Throwable, Exception {
		Logger logger = LoggerFactory.getLogger(point.getTarget().getClass());
		try {
			logger.info("[AspectJ:BEGIN] " + point.getSignature().toString());
			Object obj = point.proceed();
			logger.info("[AspectJ:END] " + point.getSignature().toString());

			return obj;

		} catch (Exception e) {
			logger.warn("EXCEPTION " + point.getSignature().toString());
			throw e;
		}
	}
}
