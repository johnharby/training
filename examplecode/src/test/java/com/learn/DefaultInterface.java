package com.learn;

public interface DefaultInterface {
    default void takeOff() {
        System.out.println("TAKING OFF");
    }

    default void land() {
        System.out.println("LANDING");
    }

    String destination();
}
