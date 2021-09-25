package com.interview;

import java.util.List;
import java.util.ArrayList;

public class Calculator {

    public static String evaluate(String expression) {
        char[] ca = expression.toCharArray();
        List<String> list = new ArrayList<>();
        String s = "";

        String op = "";
        String num1 = "";
        String num2 = "";

        boolean isOper = false;

        for (int i = 0; i < ca.length; i++) {
            if (Character.isDigit(ca[i])) {
                s += Character.toString(ca[i]);
            } else {
                list.add(s);
                list.add(Character.toString(ca[i]));

                if (isOper) {
                    isOper = false;
                    num2 = s;

                    list.set(list.lastIndexOf(num1), eval(num1, op, num2));
                    list.remove(list.lastIndexOf(op));
                    list.remove(list.lastIndexOf(num2));
                }

                if (ca[i] == '*' || ca[i] == '/') {
                    isOper = true;

                    op = Character.toString(ca[i]);
                    num1 = list.get(list.lastIndexOf(op) - 1);
                }

                s = "";
            }

            if (i == ca.length - 1 && s.length() > 0) {
                list.add(s);

                if (list.get(list.size() - 2).equals("*") || list.get(list.size() - 2).equals("/")) {
                    num1 = list.get(list.size() - 3);
                    op = list.get(list.size() - 2);
                    num2 = list.get(list.size() - 1);

                    list.set(list.size() - 3, eval(num1, op, num2));
                    list.remove(list.size() - 2);
                    list.remove(list.size() - 1);
                }
            }
        }


        while (list.size() > 1) {
            num1 = list.get(0);
            op = list.get(1);
            num2 = list.get(2);

            list.set(0, eval(num1, op, num2));
            list.remove(2);
            list.remove(1);
        }

        return list.get(0);
    }


    public static String eval(String a, String operator, String b) {
        double r = 0;

        switch (operator) {
            case "/":
                r += Double.parseDouble(a) / Double.parseDouble(b);
                break;
            case "*":
                r += Double.parseDouble(a) * Double.parseDouble(b);
                break;
            case "-":
                r += Double.parseDouble(a) - Double.parseDouble(b);
                break;
            case "+":
                r += Double.parseDouble(a) + Double.parseDouble(b);
                break;
        }

        return Double.toString(r);
    }

    public static void main(String[] args) {
        System.out.println(Calculator.evaluate("3*5/2"));
    }
}