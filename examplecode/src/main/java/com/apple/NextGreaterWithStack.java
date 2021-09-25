package com.apple;

import java.util.Arrays;
import java.util.Stack;
public class NextGreaterWithStack {
    public static int[] nextGreaterElement(int[] arr) {
        int[] output = new int[arr.length];
        Arrays.fill(output, -1);

        Stack<Integer> st = new Stack<>();
        int x = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                output[st.pop()] = arr[i];
                x++;
            }
            st.push(i);
        }
        System.out.println("WHILE LOOP count = " + x);
        return output;
    }
    public static void main(String[] args) {
 //       int arr[] = { 4, 2, 2, 6, 8, 1, 99, 7, 2, 5, 6 };
        int arr[] = new int[1000];
        for (int i = 0; i < 1000; ++i) {
            arr[i] = i;
        }
        int[] output = nextGreaterElement(arr);
//        System.out.println(Arrays.toString(output));
    }
}
