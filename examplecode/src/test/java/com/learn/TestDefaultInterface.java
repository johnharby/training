package com.learn;

import org.junit.Test;

public class TestDefaultInterface {

    @Test
    public void testDefault() {
        DefaultInterface df = () -> "Mars";
        df.takeOff();
        df.land();
        System.out.println("The destination is " + df.destination());
    }
}
