package ir.fathi.taskmanagement.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseBody
    String recordNotFoundHandler(RecordNotFoundException exception) {
        return exception.getMessage();
    }

}
