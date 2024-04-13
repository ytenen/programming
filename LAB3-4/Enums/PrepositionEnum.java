package Enums;

public enum PrepositionEnum {
    BUT("но");

    private final String incase;

    public String getIncase() {
        return incase;
    }

    PrepositionEnum(String incase) {
        this.incase = incase;
    }
}
