package ir.fathi.taskmanagement.config.aspect;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodDurationLog {
}
