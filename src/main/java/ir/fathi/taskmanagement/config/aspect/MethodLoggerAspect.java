package ir.fathi.taskmanagement.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Aspect
@Component
public class MethodLoggerAspect {

    @Around("@annotation(ir.fathi.taskmanagement.config.aspect.MethodDurationLog)")
    public Object methodDurationLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Logger logger= (Logger) LoggerFactory.getLogger(MethodLoggerAspect.class);

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
