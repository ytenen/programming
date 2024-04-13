package ThingClasses;

import Enums.PositionEnum;
import Place.Area;
import Place.Place;

public class Transport extends Thing {
    private String material;

    public Transport(String name, int weight, Place place, PositionEnum position, Area area, String material) {
        super(name, weight, place, position, area);
        this.material = material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }
}
