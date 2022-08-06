package ir.fathi.taskmanagement.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MethodLoggerAspect {
    Logger logger = LoggerFactory.getLogger(MethodLoggerAspect.class);

    @AfterThrowing(value = "execution(* ir.fathi.taskmanagement.*.*.*(..))", throwing = "exception")
    public void exceptionDetailLog(JoinPoint joinPoint, Throwable exception) {
        var info = String.format(
                """
                        there is a caught exception!
                        in method : %s ,
                        with signature : %s ,
                        with arguments : %s ,
                        caught exception %s
                        """
                , joinPoint.getSignature().toShortString(), joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()), exception.toString());
        logger.info(info);
    }

    @Around("@annotation(ir.fathi.taskmanagement.config.aspect.MethodDurationLog)")
    public Object methodDurationLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        long startTime = System.currentTimeMillis();

        result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        var info = String.format(
                """
                        method signature: %s ,
                        method name : %s ,
                        Time taken for Execution is : %d ms
                             """
                , proceedingJoinPoint.getSignature().toShortString(), proceedingJoinPoint.getSignature().getName(), duration);

        logger.info(info);
        return result;
    }

}
