package com.interview;

public class Subsequences {

    /**
     * Return all subsequences of word (as defined above) separated by commas,
     * with partialSubsequence prepended to each one.
     */
    private static String subsequencesAfter(String partialSubsequence, String word) {
        if (word.isEmpty()) {
            // base case
            return partialSubsequence;
        } else {
            // recursive step
            return subsequencesAfter(partialSubsequence, word.substring(1))
                    + ","
                    + subsequencesAfter(partialSubsequence + word.charAt(0), word.substring(1));
        }
    }

    public static String subsequences(String word) {
        return subsequencesAfter("", word);
    }

    public static void main(String[] args) {
        String s = "test";
        assert(subsequences(s).equals(",t,s,st,e,et,es,est,t,tt,ts,tst,te,tet,tes,test"));
        String t = "runner";
        System.out.println(subsequences(t));
    }
}
