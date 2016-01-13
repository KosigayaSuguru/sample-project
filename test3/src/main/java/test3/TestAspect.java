package test3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
    @Around("execution(public * test3..*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println(this.getClass().getName() + "before");
        Object obj = point.proceed();
        System.out.println(this.getClass().getName() + "after");

        return obj;
    }
}
