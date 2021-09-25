package com.learn;

import org.junit.Test;

public class InstanceMethodReference {
    interface DoSomething {
        void doStuff();
    }

    @Test
    public void testInstanceMethodRef() {
        SpaceShip spaceShip = new SpaceShip();
        DoSomething doSomething = spaceShip::flyAway;
        doSomething.doStuff();
    }

    @Test
    public void testEngineSound() {
        Engine engine = EngineSound::new;
        EngineSound es = engine.getEngineSound("Vroooom");
    }
}

class SpaceShip {
    private int fuel;
    private String captain;

    public SpaceShip() {

    }
    public SpaceShip(String captain, int fuel) {
        this.fuel = fuel;
        this.captain = captain;
    }

    public void flyAway() {
        System.out.println("FLYING AWAY");
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }
}

interface Engine {
    EngineSound getEngineSound(String engineSound);
}

class EngineSound {
    EngineSound(String engineSound) {
        System.out.println(engineSound);
    }
}

class CrewSound {
    CrewSound(String crewSound) {
        System.out.println(crewSound);
    }
}

