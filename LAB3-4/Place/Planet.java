package Place;

import Exeptions.NegativeGravitationException;

public class Planet {
    private String name;
    private int gravitation;
    private Area area;

    public Planet(String newName, int newGravitation, Area newArea) throws NegativeGravitationException {
        this.area = newArea;
        if (newGravitation < 0) {
            throw new NegativeGravitationException("Невозможное значение гравитации");
        } else {
            this.gravitation = newGravitation;
        }
        this.name = newName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGravitation(int gravitation) {
        this.gravitation = gravitation;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getGravitation() {
        return gravitation;
    }

    public Area getArea() {
        return area;
    }

    public String getName() {
        return name;
    }
}
