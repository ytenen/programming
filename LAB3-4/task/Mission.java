package task;

import CreatureClasses.Creature;
import Enums.MissionEnum;

public class Mission {
    private String name;
    private MissionEnum status;
    private Creature person;

    public Mission(String name, MissionEnum status, Creature person) {
        this.name = name;
        this.status = status;
        this.person = person;
    }

    public void setPerson(Creature person) {
        this.person = person;
    }

    public Creature getPerson() {
        return person;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(MissionEnum status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public MissionEnum getStatus() {
        return status;
    }

    public void MissionStatus() {
        System.out.print(name + " " + status.getIncase());
    }
}
