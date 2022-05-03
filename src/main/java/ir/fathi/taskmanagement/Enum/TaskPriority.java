package ir.fathi.taskmanagement.Enum;

public enum TaskPriority {

    LOW(1),
    MEDIUM(2),
    HIGH(3),
    HIGHEST(4);

    private final int label;

    TaskPriority(int label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "TaskPriority{" +
                "label=" + label +
                '}';
    }
}
