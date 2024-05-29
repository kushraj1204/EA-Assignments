package bank.aop.advice;

import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author kush
 */

@Aspect
@Component
public class DaoLogAdvice {

    final Logger logger;

    public DaoLogAdvice(Logger logger) {
        this.logger = logger;
    }


    @Before("execution(* bank.repository.*.*(..))")
    public void logBefore(JoinPoint joinpoint) {
        logger.log("Call made to "+joinpoint.getSignature().getName()+" method");
    }

}