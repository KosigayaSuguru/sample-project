package test3.app.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspectSpringAOP {

	@Around("execution(public * test3..*.*(..))")
	public Object around1(ProceedingJoinPoint point) throws Throwable {

		return impl(point);
	}

	private Object impl(ProceedingJoinPoint point) throws Throwable, Exception {
		Logger logger = LoggerFactory.getLogger(point.getTarget().getClass());
		try {
			logger.info("[SpringAOP:BEGIN] " + point.getSignature().toString());
			Object obj = point.proceed();
			logger.info("[SpringAOP:END] " + point.getSignature().toString());

			return obj;

		} catch (Exception e) {
			logger.warn("EXCEPTION " + point.getSignature().toString());
			throw e;
		}
	}
}
