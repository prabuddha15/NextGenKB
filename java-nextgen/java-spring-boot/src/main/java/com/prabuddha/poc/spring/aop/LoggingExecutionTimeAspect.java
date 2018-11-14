package com.prabuddha.poc.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*

    First, we have annotated our method with @Around. This is our advice,
    and around advice means we are adding extra code both before and after
    method execution. There are other types of advice, such as before and
    after.

    Next, our @Around annotation has a point cut argument. Our pointcut just says,
    ‘Apply this advice any method which is annotated with @LogExecutionTime.’
    There are lots of other types of pointcuts.

    Finally, when our annotated method ends up being called, what will happen is our
    advice will be called first. Then it’s up to our advice to decide what to do next.
    In our case, our advice is calculating the start time and then calling proceed(),
    which is the just calling the original annotated method.

 */



@Aspect
@Component
public class LoggingExecutionTimeAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>  "+joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
