package ir.fathi.taskmanagement.exception;

import java.util.Date;

public record ExceptionResponse(String packageName, String details, Date date) {

    public String getMassage() {
        return packageName;
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }
}
