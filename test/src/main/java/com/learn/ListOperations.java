package com.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListOperations {
    static boolean notA(String s) {
        if (!s.equals("A")) {
            return true;
        }
        return false;
    }
    public void removeAllAsFromList() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "C", "B", "A", "F", "G" ));
        List<String> newList = list.stream().filter(ListOperations::notA)
                .collect(Collectors.toList());
        newList.forEach(System.out::println);
    }

    public void removeAllNullsFromList() {
        List<String> list = new ArrayList<>(Arrays.asList("A", null, "B", null));
        List<String> newList = list.stream().filter(Objects::nonNull)
                .collect(Collectors.toList());
        newList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        ListOperations lo = new ListOperations();
      //  lo.removeAllNullsFromList();
        lo.removeAllAsFromList();
    }
}
