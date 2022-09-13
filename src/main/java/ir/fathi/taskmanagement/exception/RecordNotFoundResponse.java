package ir.fathi.taskmanagement.exception;

import org.springframework.http.HttpStatus;


import java.time.LocalDateTime;

public record RecordNotFoundResponse(
        HttpStatus statusCode ,
        String message ,
        LocalDateTime issueTime
) {

    public RecordNotFoundResponse(HttpStatus statusCode, String message, LocalDateTime issueTime) {
        this.statusCode = statusCode;
        this.message = message;
        this.issueTime = issueTime;
    }
}
