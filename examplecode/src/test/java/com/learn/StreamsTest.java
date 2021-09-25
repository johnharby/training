package com.learn;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsTest {
    List<SpaceShip> ships() {
        List<SpaceShip> ships = new ArrayList<>();
        ships.add(new SpaceShip("Jim", 1));
        ships.add(new SpaceShip("Al", 2));
        ships.add(new SpaceShip("Bob", 3));
        ships.add(new SpaceShip("Ted", 4));
        ships.add(new SpaceShip("Steve", 5));
        ships.add(new SpaceShip("Joe", 6));
        ships.add(new SpaceShip("Evan", 7));
        ships.add(new SpaceShip("Igor", 8));
        ships.add(new SpaceShip("Joe", 8));
        return ships;
    }

    @Test
    public void testStreams() {
        List<SpaceShip> ships = ships();

        List<String> listOfCaptains = ships
                .stream()
                .filter((ship) -> ship.getFuel() >= 5)
                .map((ship) -> ship.getCaptain())
                .sorted((capt1, capt2) -> capt1.compareTo(capt2))
                .collect(Collectors.toList());

        listOfCaptains.forEach(System.out::println);
    }

    @Test
    public void testParallelStreams() {
        List<SpaceShip> ships = ships();

        List<String> listOfCaptains = ships
                .parallelStream()
                .filter((ship) -> ship.getFuel() >= 5)
                .map((ship) -> ship.getCaptain())
                .sorted((capt1, capt2) -> capt1.compareTo(capt2))
                .collect(Collectors.toList());

        listOfCaptains.forEach(System.out::println);
    }
}
