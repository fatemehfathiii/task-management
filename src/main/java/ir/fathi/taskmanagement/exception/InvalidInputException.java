package ir.fathi.taskmanagement.exception;

public class InvalidInputException extends Exception{
    public InvalidInputException(Object obj) {
        super("input for"+obj+"parameter");
    }
}
