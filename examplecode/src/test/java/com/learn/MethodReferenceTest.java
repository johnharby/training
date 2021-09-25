package com.learn;

import org.junit.Test;
import java.util.function.BiFunction;

public class MethodReferenceTest {
    interface NoiseMaker {
        void noise();
    }

    static void MetallicNoise() {
        System.out.println("Kling klang ding");
    }

    @Test
    public void testMethodRef() {
        NoiseMaker ns = MethodReferenceTest::MetallicNoise;
        ns.noise();
    }

    static int someMath(int a, int b) {
        return (a+b) * 10;
    }

    @Test
    public void testBiFunction() {
        BiFunction<Integer, Integer, Integer> function = MethodReferenceTest::someMath;
        Integer result = function.apply(10, 20);
        System.out.println("Result " + result);
    }
}
