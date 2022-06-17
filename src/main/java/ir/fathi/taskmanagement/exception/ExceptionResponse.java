package ir.fathi.taskmanagement.exception;

import java.util.Date;

public class ExceptionResponse {
    private final String pakageName;
    private final String details;
    private final Date date;

    public ExceptionResponse(String pakageName, String details, Date date) {
        this.pakageName = pakageName;
        this.details = details;
        this.date = date;
    }

    public String getMassage() {
        return pakageName;
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }
}
