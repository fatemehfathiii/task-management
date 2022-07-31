package ir.fathi.taskmanagement.enumType;

public enum Sex {
    MALE(0),
    FEMALE(1);

    private final int label;

    Sex(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "label=" + label +
                '}';
    }
}
