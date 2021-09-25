package com.learn;

import java.util.ArrayList;
import java.util.List;

public class TailRecursion {

    public static void main(String[] args) {
        List<FireCracker> fireCrackers = new ArrayList();
        for (int i = 0; i < 20; i++) {
            String explosion = "b";
            for (int o = 0; o <= i; o++) {
                explosion += "o";
            }
            explosion += "m\n";
            fireCrackers.add(new FireCracker(explosion));
        }
        System.out.println(fireCrackers.get(0).light(
                fireCrackers.subList(1, fireCrackers.size())
        ));
    }

    static class FireCracker {
        private String explosion;

        public FireCracker(String explosion) {
            this.explosion = explosion;
        }


        public String light(List<FireCracker> fireCrackers) {
            if (fireCrackers.size() == 0) {
                return explosion;
            }
            else {
                return explosion + fireCrackers.get(0).
                        light(fireCrackers.subList(1, fireCrackers.size()));
            }
        }
    }
}
