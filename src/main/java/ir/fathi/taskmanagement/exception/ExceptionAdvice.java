package ir.fathi.taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ExceptionAdvice{

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RecordNotFoundResponse handleRecordNotFoundException(RecordNotFoundException exception ){
        return new RecordNotFoundResponse
                (HttpStatus.NOT_FOUND , exception.getMessage() , exception.getTime());

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<MethodArgumentNotValidResponse> handelValidationError(MethodArgumentNotValidException exception ){

        List<MethodArgumentNotValidResponse> validationErrors =new ArrayList<>();

        exception.getBindingResult().getAllErrors()
               .forEach(error -> {
                   String fieldName=((FieldError)error).getField();
                   String errorMessage=error.getDefaultMessage();
                  validationErrors.add(new MethodArgumentNotValidResponse(fieldName,errorMessage));
               });
       return validationErrors;
    }

}
