package CreatureClasses;

import Enums.Gender;
import Matter.Matter;
import Place.Area;
import Place.Place;
import interfaces.CreatureMove;
import interfaces.CreatureNonMoveAction;

public class Creature extends Matter implements CreatureMove, CreatureNonMoveAction {
    private String name;
    private double height;
    private Gender sex;

    public Creature(String newName, Place newPlace, int newWeight, Area newArea, Gender newGender) {
        super(newName, newWeight, newPlace, newArea);
        this.sex = newGender;
        calculateHeight();
    }

    public void setGender(Gender sex) {
        this.sex = sex;
    }

    // CreatureMove
    @Override
    public void walk() {
        System.out.print(" зашагал по");
    }

    @Override
    public void take() {
        System.out.print(name + " взял");
    }

    @Override
    public void lookingInTheWindow() {
        System.out.print(name + " смотрели в иллюминаторы");
    }

    @Override
    public void joyLookin() {
        System.out.print(" радовались, видя как ");
    }

    @Override
    public void beeingNearBy() {
        System.out.print(" был недалеко от ");
    }

    @Override
    public void consider() {
        System.out.print(" считал");
    }

    @Override
    public void saw() {
        System.out.print(" увидел, что");
    }

    @Override
    public void setOff() {
        System.out.print("выключили ");
    }

    @Override
    public void shout() {
        System.out.print(" закричал");
    }

    // CreatureNonMoveAction
    @Override
    public void whatJokes() {
        System.out.print("Что за шуточки?");
    }

    @Override
    public void decide() {
        System.out.print(" решил");
    }

    @Override
    public void feel() {
        System.out.print(" почувствовал ");
    }

    @Override
    public void notice() {
        System.out.print(" заметил что");
    }

    @Override
    public void thinkToPrank() {
        System.out.print("коротышки задумали подшутить");
    }

    public Gender getGender() {
        return sex;
    }

    public double getHeight() {
        return height;
    }

}
