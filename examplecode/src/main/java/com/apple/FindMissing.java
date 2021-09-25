package com.apple;

public class FindMissing {

    public static void findMissing(int[] arr) {
        int[] register = new int[100];
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            register[i] = 1;
        }
        for (int i = 0; i < register.length; ++i) {
            if (register[i] == 0) {
                sb.append(i);
                if (i < register.length - 1) {
                    if (register[i + 1] == 1) {
                        sb.append(",");
                    }
                    else {
                        sb.append("-");
                        while (i < register.length - 1 && register[i+1]  == 0) {
                            i++;
                        }
                        if (i == register.length - 1) {
                            sb.append(i);
                        }
                        else {
                            sb.append(i + ",");
                        }
                    }
                }
            }
        }
        String s = sb.toString();
        if (s.endsWith(",")) {
            System.out.println(s.substring(0, s.length() - 1));
        }
        else {
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 5, 6, 97};
        findMissing(arr);
        int[] arr2 = {2, 3, 4, 99};
        findMissing(arr2);
        int[] arr3 = {1, 2, 3, 4};
        findMissing(arr3);
    }
}

