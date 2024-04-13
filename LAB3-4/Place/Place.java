package Place;

public class Place {
    private String name;
    private Area area;
    private Planet planet;

    public Place(String newName, Area newArea, Planet newPlanet) {
        this.area = newArea;
        this.name = newName;
        this.planet = newPlanet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Area getArea() {
        return area;
    }

    public Planet getPlanet() {
        return planet;
    }

    public String getName() {
        return name;
    }
}
