package ir.fathi.taskmanagement.enumType;

public enum TaskType {

    UNDEFINED(0),
    FixedWork(1),
    FixedDuration(2),
    FixedUnits(3);


    private final int label;

    TaskType(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "TaskType{" +
                "label=" + label +
                '}';
    }
}
