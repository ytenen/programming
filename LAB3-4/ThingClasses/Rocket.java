package ThingClasses;

import Enums.PositionEnum;
import Enums.ModEnum;
import Exeptions.ZeroPassengersException;
import Place.Area;
import Place.Place;

public class Rocket extends Transport {
    public Rocket(String name, int weight, Place place, PositionEnum position, Area area, String material) {
        super(name, weight, place, position, area, material);
    }

    private weightlessnessDevice device = new weightlessnessDevice("прибор невесомости", ModEnum.OFF);
    private Cabin cabin = new Cabin(3, 0);

    private class weightlessnessDevice {
        private String model;
        private ModEnum mode;

        private weightlessnessDevice(String model, ModEnum mode) {
            this.model = model;
            this.mode = mode;
        }
    }

    private class Cabin {
        private int maxPassengers;
        private int passengersCount;

        private Cabin(int maxPassengers, int passengersCount) {
            this.maxPassengers = maxPassengers;
            if (passengersCount < 0) {
                throw new ZeroPassengersException("Сообщение: нет пассажиров");
            } else {
                this.passengersCount = passengersCount;
            }
        }
    }

    public static class Engine {
        ModEnum mode;
    }

    public ModEnum getWeightlessnessDeviceMode() {
        return device.mode;
    }

    public int getRocketWeight() {
        class Material {
            int density;
        }
        Material m1 = new Material();
        m1.density = 660;
        return this.getWeight() + m1.density;
    }

    public String getWeightlessnessDeviceModel() {
        return device.model;
    }

    public int getPassengersCount() {
        return cabin.passengersCount;
    }

    public int getMaxPassengers() {
        return cabin.maxPassengers;
    }
}
