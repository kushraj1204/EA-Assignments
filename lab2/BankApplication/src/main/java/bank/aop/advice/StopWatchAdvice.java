package bank.aop.advice;

import bank.integration.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import java.util.UUID;

/**
 * @author kush
 */
@Aspect
@Configuration
public class StopWatchAdvice {
    final Logger logger;

    public StopWatchAdvice(Logger logger) {
        this.logger = logger;
    }
    @Around("execution(* bank.service.*.*(..))")
    public Object profile (ProceedingJoinPoint call) throws Throwable{
        StopWatch clock = new StopWatch(UUID.randomUUID().toString());
        clock.start(call.toShortString());
        Object object= call.proceed();
        clock.stop();
        logger.log(clock.prettyPrint());
        return object;
    }
}
