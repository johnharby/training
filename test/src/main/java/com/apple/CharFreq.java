package com.apple;

import java.util.HashMap;
import java.util.Map;

public class CharFreq {
    public static void main(String[] args) {
        String s = "test";
        int len = s.length();
 //       char[] ca = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char ca : s.toCharArray()) {
            if (map.get(ca) == null) {
                map.put(ca, 1);
            }
            else {
                map.put(ca, map.get(ca) + 1);
            }
        }
        System.out.println(map);
    }
}
