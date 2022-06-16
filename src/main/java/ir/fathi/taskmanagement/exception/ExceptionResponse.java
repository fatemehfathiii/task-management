package ir.fathi.taskmanagement.exception;

import java.util.Date;

public class ExceptionResponse {
    private final String massage;
    private final String details;
    private final Date date;

    public ExceptionResponse(String massage, String details, Date date) {
        this.massage = massage;
        this.details = details;
        this.date = date;
    }

    public String getMassage() {
        return massage;
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }
}
