package Enums;

public enum PositionEnum {
    VERTICALLY("вертикальное"),
    HORIZONTALLY("горизонтальное");

    private final String incase;

    public String getIncase() {
        return incase;
    }

    PositionEnum(String incase) {
        this.incase = incase;
    }
}
