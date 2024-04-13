package ThingClasses;

import Enums.PositionEnum;
import Matter.Matter;
import Place.Area;
import Place.Place;

public class Thing extends Matter {
    private double height;
    private PositionEnum position;

    public Thing(String newName, int newWeight, Place newPlace, PositionEnum newPosition, Area newArea) {
        super(newName, newWeight, newPlace, newArea);
        this.position = newPosition;
        calculateHeight();
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public double getHeight() {
        return height;
    }
}
