package org.example.aop.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.entity.ErrorLog;
import org.example.services.ErrorLogService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Aspect
@RequiredArgsConstructor
@Component
public class ErrorCatchAndLoggingAspect {

    private final ErrorLogService errorLogService;

    @Pointcut("@annotation(org.example.aop.annotation.Logging)")
    public void LoggingMethods() {
    }

    @Pointcut("execution(public * org.example.services..*(..))")
    public void ErrorCatchMethods() {

    }

    @AfterThrowing(pointcut = "ErrorCatchMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String errorMessage = exception.getMessage();
        String errorClass = exception.getClass().getSimpleName();

        log.error("Exception in method: {}.{}() - Exception: {} - Message: {}",
                className, methodName, errorClass, errorMessage);

        ErrorLog errorLog = new ErrorLog();
        errorLog.setErrorClass(errorClass);
        errorLog.setErrorMessage(errorMessage);

        errorLogService.saveError(errorLog);

    }

    @Around("LoggingMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed();
        log.info("\n LoggingAspect is triggered for method: {}, in class: {} \n Method arguments: {} \n Method result: {}", methodName, className, Arrays.toString(args), result );
        return result;
    }
}
