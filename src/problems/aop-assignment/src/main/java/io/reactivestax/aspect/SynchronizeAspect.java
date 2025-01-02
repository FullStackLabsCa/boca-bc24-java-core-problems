package io.reactivestax.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SynchronizeAspect {
    @Pointcut("@annotation(io.reactivestax.annotation.Synchronized)")
    public void synchronizedAnnotation() {
    }

    @Around("synchronizedAnnotation()")
    public void synchronizing(ProceedingJoinPoint joinPoint) throws Throwable {
        synchronized (this) {
            System.out.println("Synchronizing started");
            joinPoint.proceed();
            System.out.println("Synchronizing ended");
        }
    }
}
