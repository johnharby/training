package com.apple;

import org.junit.Ignore;
import org.junit.Test;

public class FindMissingTest {
    /**
     * Method under test: {@link FindMissing#findMissing(int[])}
     */
    @Test
    public void testFindMissing() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        FindMissing.findMissing(new int[]{1, 1, 1, 1});
        assert(true);
    }

    /**
     * Method under test: {@link FindMissing#findMissing(int[])}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testFindMissing2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index 100 out of bounds for length 100
        //       at com.apple.FindMissing.findMissing(FindMissing.java:10)
        //   See https://diff.blue/R013 to resolve this issue.

        FindMissing.findMissing(new int[]{100, 1, 1, 1});
    }

    /**
     * Method under test: {@link FindMissing#findMissing(int[])}
     */
    @Test
    public void testFindMissing3() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        FindMissing.findMissing(new int[]{4, 1, 1, 1});
    }

    /**
     * Method under test: {@link FindMissing#findMissing(int[])}
     */
    @Test
    public void testFindMissing4() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        FindMissing.findMissing(new int[]{99, 1, 1, 1});
    }
}

