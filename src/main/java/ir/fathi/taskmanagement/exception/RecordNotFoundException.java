package ir.fathi.taskmanagement.exception;

import java.util.Date;

public class RecordNotFoundException extends Exception{

    private Date time;

    public RecordNotFoundException(Object obj) {
        super("Record not found (param :"+obj+")");
    }

    public RecordNotFoundException() {
        super("Record not found");
    }

    public String getMessage(String message) {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
