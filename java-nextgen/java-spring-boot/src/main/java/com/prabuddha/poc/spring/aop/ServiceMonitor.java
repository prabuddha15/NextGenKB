package com.prabuddha.poc.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*

Aspect: An aspect is a class that implements enterprise application concerns
        that cut across multiple classes, such as transaction management. Aspects
        can be a normal class configured through Spring XML configuration or we
        can use Spring AspectJ integration to define a class as Aspect using
        @Aspect annotation.

Join Point: A join point is the specific point in the application such as method
            execution, exception handling, changing object variable values etc.
            In Spring AOP a join points is always the execution of a method.
 */

@Aspect
@Component
public class ServiceMonitor {

    @AfterReturning("execution(* sample..*Service.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        //This method will print the statement returned from HelloWorldService.getHelloMessage()
        //System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>  Completed: " + joinPoint);
    }

}
