package Main;

import CreatureClasses.Creature;
import Enums.Gender;
import Enums.MissionEnum;
import Enums.PositionEnum;
import Enums.PrepositionEnum;
import Exeptions.NegativeGravitationException;
import Exeptions.ZeroPassengersException;
import Place.Area;
import Place.Place;
import Place.Planet;
import ThingClasses.Rocket;
import ThingClasses.Thing;
import task.Mission;

public class main {
    public static void main(String[] args) throws NegativeGravitationException, ZeroPassengersException {
        //1
        Area a1 = new Area(1, 1, 1, 1, false);
        Planet abstPlanet = new Planet("Abstract planet", 0, a1);
        Place abstPLace = new Place("abstract place", a1, abstPlanet);
        Thing allThing = new Thing("Все предметы", 1, abstPLace, PositionEnum.VERTICALLY, a1);
        allThing.looseWeight();
        allThing.riseUp();
        System.out.print(". ");

        //2
        Thing thing = new Thing("Предмет", 0, abstPLace, PositionEnum.VERTICALLY, a1);
        thing.beeingUnderPressureForce();
        thing.shrinkOnSmallValue();
        System.out.print(". ");

        //3
        thing.looseWeight();
        thing.unshrink();
        thing.push();
        Thing surface = new Thing(" поверхности", 0, abstPLace, PositionEnum.VERTICALLY, a1);
        surface.stood();
        System.out.print(".");

        //4
        Area moonArea = new Area(20, 20, 300, 100, false);
        Planet moon = new Planet("Луна", 4, moonArea);
        Place moonRoad = new Place("лунная дорожка", moonArea, moon);
        Area znaikaArea = new Area(21, 21, 300, 1, true);
        Area rocketArea = new Area(21, 21,310 , 20, false);
        Creature znaika = new Creature("Знайка", moonRoad, 40, znaikaArea, Gender.MALE);
        System.out.println(znaika.getName());
        znaika.notice();
        Rocket rocket = new Rocket(" ракета", 20000, moonRoad, PositionEnum.VERTICALLY, rocketArea, "steel");
        System.out.print(rocket.getName());
        rocket.riseUp();
        System.out.print(",");
        znaika.pull();
        Area ropArea = new Area(21, 21, 305, 2, false);
        Thing rope = new Thing(" шнур", 2, moonRoad, PositionEnum.VERTICALLY, ropArea);
        System.out.print(rope.getName());
        System.out.print(", "+znaika.getName());
        znaika.walk();
        System.out.print(" "+znaika.getPlace().getName()+".");

        //5
        rocket.takeCondition();
        System.out.print(" "+PositionEnum.HORIZONTALLY.getIncase());
        rocket.swimAbove();
        System.out.print(surface.getName()+" ");
        System.out.print(rocket.getPlace().getPlanet().getName()+".");

        //6
        rocket.fall();
        rocket.barelyTouch();
        System.out.print(surface.getName()+" ");
        System.out.print(rocket.getPlace().getPlanet().getName());
        rocket.pushAnd();
        rocket.riseUp();
        System.out.print(".");

        //7
        Creature dwarfs = new Creature("коротышки",moonRoad,35,rocketArea,Gender.MALE){
            @Override
            public void lookingInTheWindow(){
                System.out.print("Коротышки смотрели в иллюминаторы");
            }
            @Override
            public void thinkToPrank(){
                System.out.print(" коротышки задумали подшутить ");
            }
        };
        dwarfs.lookingInTheWindow();
        System.out.print(".");

        //8
        Creature everyone = new Creature("Все", moonRoad, 0, rocketArea, Gender.MALE);
        System.out.print(everyone.getName());
        everyone.joyLookin();
        znaika.pull();
        System.out.print(rocket.getName()+".");

        //9
        System.out.print(znaika.getName());
        znaika.beeingNearBy();
        Area caveArea = new Area(42, 39, 300, 25, false);
        Place cave = new Place("пещера", caveArea, moon);
        System.out.print(cave.getName());
        znaika.consider();
        Mission miss = new Mission(" задача", MissionEnum.COMPLETED,znaika);
        miss.MissionStatus();
        System.out.print(" "+PrepositionEnum.BUT.getIncase());
        rocket.fall();
        System.out.print(".");

        //10
        System.out.print(znaika.getName());
        znaika.saw();
        rocket.notPush();
        System.out.print(surface.getName()+" ");
        System.out.print(rocket.getPlace().getPlanet().getName()+" "+znaika.getName());
        znaika.feel();
        znaika.cantMoveFrom();
        Place place = new Place("место", caveArea, moon);
        System.out.print(place.getName()+" "+rocket.getName()+".");
        
        //11
        System.out.print(znaika.getName());
        znaika.decide();
        dwarfs.thinkToPrank();
        System.out.print(znaika.getName());
        znaika.shout();
        System.out.print(":");

        //12
        System.out.println();
        znaika.whatJokes();
        Creature you = new Creature("Вы ", cave, 0, caveArea, Gender.MALE);
        System.out.print(you.getName());
        you.setOff();
        System.out.print(rocket.getWeightlessnessDeviceModel()+"?");
    }
}
