package org.example.aop.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.services.MethodsTimeTrackService;
import org.example.services.impl.MethodsTimeTrackServiceImpl;
import org.springframework.stereotype.Component;
import org.example.aop.annotation.TrackTime;
import org.example.aop.annotation.TrackAsyncTime;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TimeTrackingAspect {

    private final MethodsTimeTrackService methodsTimeTrackService;

    @Pointcut("@annotation(org.example.aop.annotation.TrackTime)")
    public void hasTrackTime() {
    }

    @Pointcut("@annotation(org.example.aop.annotation.TrackAsyncTime)")
    public void hasTrackAsyncTime() {
    }

    @Around("hasTrackTime()")
    public Object addTrackTimeForMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTimeMethod = System.currentTimeMillis();
        try {
        Object result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTimeMethod;
        log.info("Method : {} executed synchronously in {} ms", joinPoint.getSignature(), timeTaken);
        methodsTimeTrackService.saveExecutionTime(joinPoint.getSignature().toString(), timeTaken);
        return result;
        } catch (Throwable throwable) {
            long timeTaken = System.currentTimeMillis() - startTimeMethod;
            log.error("Method : {} failed after {} ms with exception: {}", joinPoint.getSignature(), timeTaken, throwable.getMessage());
            methodsTimeTrackService.saveExecutionTime(joinPoint.getSignature().toString(), timeTaken);
            throw throwable;
        }
    }

    @Around("hasTrackAsyncTime()")
    public Object addTrackAsyncTimeForMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTimeMethod = System.currentTimeMillis();
        CompletableFuture<?> future = (CompletableFuture<?>) joinPoint.proceed();
        return future.whenComplete((result, exception) -> {
            long timeTaken = System.currentTimeMillis() - startTimeMethod;
            if (exception != null) {
                log.error("Method : {} failed asynchronously after {} ms with exception: {}", joinPoint.getSignature(), timeTaken, exception.getMessage());
            } else {
                log.info("Method : {} executed asynchronously in {} ms", joinPoint.getSignature(), timeTaken);
            }
            methodsTimeTrackService.saveExecutionTime(joinPoint.getSignature().toString(), timeTaken);
        });
    }

}