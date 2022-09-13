package ir.fathi.taskmanagement.exception;

public record MethodArgumentNotValidResponse(
        String fieldName,
        String errorMessage
) {

    public MethodArgumentNotValidResponse(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;

    }
}
