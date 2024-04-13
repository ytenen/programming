package Enums;

public enum MissionEnum {
    COMPLETED("выполнено"),
    NOTCOMPLETED("не выполнено");

    private final String incase;

    public String getIncase() {
        return incase;
    }

    MissionEnum(String incase) {
        this.incase = incase;
    }
}
