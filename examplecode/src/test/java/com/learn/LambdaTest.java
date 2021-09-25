package com.learn;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class LambdaTest {

    @Test
    public void test() {
        Runnable r = () -> {
            System.out.println("This is a runnable");
        };
        r.run();
    }

    @FunctionalInterface
    interface CargoSpace {
        String content(int qty, String stuff);
    }

    @Test
    public void lambdaWithArguments() {
        CargoSpace cs = (qty, stuff) -> "We have " + qty + " pcs of " + stuff;
        CargoSpace cs2 = (qty, stuff) -> {
            return "We have some (" + qty + ") quantity of " + stuff;
        };
        System.out.println(cs.content(10, "Bananas"));
        System.out.println(cs2.content(20, "Oranges"));
    }

    @Test
    public void listWithLoopLambda() {
        List<String> cargoTypes = new ArrayList<>();
        cargoTypes.add("Bananas");
        cargoTypes.add("Oranges");
        cargoTypes.add("Coconuts");
        cargoTypes.add("Wheat");

        cargoTypes.forEach(System.out::println);
        cargoTypes.forEach((s) -> {
            System.out.println("The cargotype could be " + s);
        });
    }

}
