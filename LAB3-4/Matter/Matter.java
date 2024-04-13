package Matter;

import java.util.Objects;

import Place.Area;
import Place.Place;
import interfaces.Condition;
import interfaces.MatterMove;
import interfaces.Pushable;
import interfaces.StaticActions;
import interfaces.VerticallMoves;
import interfaces.WeightMoves;

public abstract class Matter implements Condition, MatterMove, Pushable, VerticallMoves, WeightMoves, StaticActions {
    private String name;
    private int weight;
    private Place place;
    private Area area;
    private double height;

    static {
        class X{

        }
    }

    public Matter(String name, int weight, Place place, Area area) {
        this.name = name;
        this.weight = weight;
        this.place = place;
        this.area = area;
        calculateHeight();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void calculateHeight() {
        this.height = Math.sqrt(place.getPlanet().getGravitation() * 2 / place.getPlanet().getArea().getR());
    }

    // Condition
    @Override
    public void shrinkOnSmallValue() {
        System.out.print(" сжимается на маленькую величину");
    }

    @Override
    public void unshrink() {
        System.out.print(" разжимается");
    }

    @Override
    public void stood() {
        System.out.print(name + " на которой стоял");
    }

    // MatterMove
    @Override
    public void cantMoveFrom() {
        System.out.print(name + " не мог сдвинуть с ");
    }

    @Override
    public void notPush() {
        System.out.print(name + " не оттолкнулась от ");
    }

    @Override
    public void beeingUnderPressureForce() {
        System.out.print(name + " находясь под силой тяжести");
    }

    @Override
    public void riseUp() {
        System.out.print(" поднимаются вверх");
    }

    @Override
    public void pull() {
        System.out.print(name + " потянул");
    }

    @Override
    public void looseWeight() {
        System.out.print(name + " теряя вес");
    }

    @Override
    public void push() {
        System.out.print(" отталкивается");
    }

    @Override
    public void pushAnd() {
        System.out.print(" отталкивается и");
    }

    @Override
    public void swimAbove() {
        System.out.print(name + " поплыла над");
    }

    @Override
    public void fall() {
        System.out.print(name + " опускалась");
    }

    @Override
    public void takeCondition() {
        System.out.print(name + " приняла положение");
    }

    @Override
    public void barelyTouch() {
        System.out.print(" едва касаясь");
    }

    public int getWeight() {
        return weight;
    }

    public Place getPlace() {
        return place;
    }

    public Area getArea() {
        return area;
    }

    public String getName() {
        return name;
    }

    // Object
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Matter))
            return false;
        Matter m1 = (Matter) obj;
        return Objects.equals(m1.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
