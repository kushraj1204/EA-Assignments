package edu.miu.Lab2PartB.customers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author kush
 */
@Aspect
@Component
public class TimerAdvice {

    @Around("execution(* edu.miu.Lab2PartB.customers.CustomerRepository.*(..))")
    public Object invoke(ProceedingJoinPoint call ) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        System.out.println("Took "+totaltime+" ms for the method execution");
        return retVal;
    }
}

