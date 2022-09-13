package ir.fathi.taskmanagement.exception;

import java.time.LocalDateTime;


public class RecordNotFoundException extends Exception{

    private LocalDateTime time;

    public RecordNotFoundException(LocalDateTime time , String param) {
        super("record not found with this param : "+param);
        this.time = time;
    }

    public RecordNotFoundException( LocalDateTime time ) {
        super("record not found ");
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
