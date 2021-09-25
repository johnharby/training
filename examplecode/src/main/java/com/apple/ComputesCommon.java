package com.apple;

public class ComputesCommon {

    public static String computeCommon(String s) {
        char[] ca = s.toCharArray();
        char prev = ca[0];
        int cnt = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < ca.length; ++i) {
            if (ca[i] == prev) {
                cnt++;
                if (i == ca.length - 1) {
                    sb.append(prev + "," + cnt + " ");
                }
            }
            else {
                sb.append(prev + "," + cnt + " ");
                cnt = 1;
                prev = ca[i];
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {

        System.out.println(ComputesCommon.computeCommon("aaabbccaaabbzzzzzrrrrzz"));
    }
}
