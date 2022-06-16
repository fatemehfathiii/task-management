package ir.fathi.taskmanagement.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class MethodLoggerAspect {

    Logger logger= (Logger) LoggerFactory.getLogger(MethodLoggerAspect.class);


    @AfterThrowing(value = "execution(* ir.fathi.taskmanagement.controller.*.*(..))",throwing = "exception")
    public void exceptionDetailLog(JoinPoint joinPoint,Throwable exception){

        var signature=joinPoint.getSignature().toShortString();
        var method=joinPoint.getSignature().getName();
        var arg= Arrays.toString(joinPoint.getArgs());
        var info=String.format(
                """
                        there is a caught exception!
                        in method : %s ,
                        with signature : %s ,
                        with arguments : %s ,
                        caught exception %s
                        """
        ,method,signature,arg,exception.getMessage());
        logger.info(info);
    }

    @Around("@annotation(ir.fathi.taskmanagement.config.aspect.MethodDurationLog)")
    public Object methodDurationLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        Object result;
        long startTime = System.currentTimeMillis();

        result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        logger.info(
                "method signature:" + proceedingJoinPoint.getSignature().toShortString() +
                        "\n method name :" + proceedingJoinPoint.getSignature().getName() +
                        "\nTime taken for Execution is :" + duration + "ms"
        );
        return result;
    }

}
