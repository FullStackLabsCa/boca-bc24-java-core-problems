package io.reactivestax.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAspect {
    @Pointcut("@annotation(io.reactivestax.annotation.Timer)")
    public void timerAnnotation() {
    }

    @Around("timerAnnotation()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("Starting Timer: " + startTime + "ms for method: " + joinPoint.getSignature().getName());

        Object returnValue = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println("Ending Timer: " + endTime + "ms for method: " + joinPoint.getSignature().getName());
        System.out.println("Total Duration: " + (endTime - startTime) + " ms");

        return returnValue;
    }
}
