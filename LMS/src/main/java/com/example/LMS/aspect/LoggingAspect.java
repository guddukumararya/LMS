package com.example.LMS.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.LMS.service.*.*(..))")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String returnType = method.getReturnType().getName();
        String modifier = Modifier.toString(method.getModifiers());
        String methodName = method.getName();
        StringBuilder logMethodName = new StringBuilder();
        String modRetmet = modifier + " " + returnType + " " + methodName + "(";
        logMethodName.append(modRetmet);
        Class[] parametersType = methodSignature.getParameterTypes();
        String[] parametersName = methodSignature.getParameterNames();
        for (int i = 0; i < parametersType.length; i++) {
            String parameterType = parametersType[i].toString();
            String parTypeName = parameterType + " " + parametersName[i];
            logMethodName.append(parTypeName);
            if (i != parametersType.length - 1) {
                logMethodName.append(",");
            }
        }
        logMethodName.append(")");
        log.info("Entering : " + logMethodName);
        Object object = joinPoint.proceed();
        log.info("Exiting : " + logMethodName);
        return object;
    }
}
