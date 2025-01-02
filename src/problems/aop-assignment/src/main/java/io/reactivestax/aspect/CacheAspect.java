package io.reactivestax.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CacheAspect {

    private final Map<String, Object> cacheMap = new HashMap<>();

    @Pointcut("@annotation(io.reactivestax.annotation.Cache)")
    public void cacheAnnotation() {
    }

    @Around("cacheAnnotation()")
    public Object handleCaching(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = Arrays.toString(joinPoint.getArgs()).toLowerCase();

        if (cacheMap.containsKey(key)) {
            System.out.println("Cache hit: " + key);
            return cacheMap.get(key);
        }
        Object returnValue = joinPoint.proceed();

        cacheMap.put(key, returnValue);
        System.out.println("Cache miss: " + key);

        return returnValue;
    }

    @Pointcut("@annotation(io.reactivestax.annotation.CacheEvict)")
    public void cacheEvictAnnotation() {
    }

    @Around("cacheEvictAnnotation()")
    public Object handleCacheEviction(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = Arrays.toString(joinPoint.getArgs()).toLowerCase();

        if (cacheMap.containsKey(key)) {
            System.out.println("Evicting cache for key: " + key);
            cacheMap.remove(key);
        }
        return joinPoint.proceed();
    }

    @Around("cacheEvictAnnotation() && cacheAnnotation()")
    public Object handleCacheAndEvictingCache(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = Arrays.toString(joinPoint.getArgs()).toLowerCase();

        if (cacheMap.containsKey(key)) {
            System.out.println("Evicting cache for key: " + key);
            cacheMap.remove(key);
        }
        Object returnValue = joinPoint.proceed();

        cacheMap.put(key, returnValue);
        System.out.println("Cache miss: " + key);

        return returnValue;
    }
}
