package ir.fathi.taskmanagement.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Aspect
@Component
public class MethodDurationLoggerAspect {

    @Around("@annotation(ir.fathi.taskmanagement.config.aspect.MethodDurationLog)")
    public Object methodDurationLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Logger logger= (Logger) LoggerFactory.getLogger(MethodDurationLoggerAspect.class);

        Object result;
        long startTime = System.currentTimeMillis();

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable exception) {
            throw exception;
        }

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
