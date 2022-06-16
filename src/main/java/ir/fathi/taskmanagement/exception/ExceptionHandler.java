package ir.fathi.taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(RecordNotFoundException.class)
    ResponseEntity<ExceptionResponse> recordNotFoundException(RecordNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(), webRequest.getDescription(false), new Date()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ExceptionResponse> badCredentialsException(BadCredentialsException exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(), webRequest.getDescription(false), new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    ResponseEntity<ExceptionResponse> exception(Exception exception, WebRequest webRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(), webRequest.getDescription(false), new Date()
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
