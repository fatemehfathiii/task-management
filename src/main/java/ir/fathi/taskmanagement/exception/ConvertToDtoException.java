package ir.fathi.taskmanagement.exception;

public class ConvertToDtoException extends Exception{

    public ConvertToDtoException(Object obj) {
        super("conversion failed\n"+obj+"is unknown object");
    }
}
