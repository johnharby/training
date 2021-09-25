// The Lottery Calculator program

// CIS36a Lab 2

// John Harby

package com.deanza;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Lottery {
    private static final int[] winner = {1, 9, 15, 33, 40};
    
    /**
     *  formResponse accepts the total amount won and returns the appropriate
     *  string.
     *
     * @param total
     * @return
     */
    private static String formResponse(int total) {
        if (total == 0) {
            return "Sorry, you have no matches";
        }
        else if (total > 0){
            return "You matched " + total /10 + " numbers! " + "You win $" + total;
        }
        return "";
    }

    /**
     * processUserEntries uses the input Scanner to read the values from the
     * user and determine which ones match the winner. The total winnings are
     * returned or -1 to indicate the game is over.
     *
     * @param total
     * @param scan
     * @return The total won on the lottery ticket or for a -1 entry will
     *         return -1 or if one of the numbers entered is out of the range
     *         1 to 55 will return -2 as a flag so the user can try again.
     */
    private static int processUserEntries(int total, Scanner scan) {
        Integer guess;
        int i = 0;
        List<Integer> guessList = new ArrayList<>();
        while (i < 5) {
            guess = scan.nextInt();
            if (guess == -1) {
                total = -1;
                return total;
            }
            if (guess != -1 && (guess <= 0 || guess > 55)) {
                System.out.println("You must enter -1 or five numbers " +
                        "that are between 1 and 55 inclusive.");
                total = -2;
                return -2;
            }
            if (!guessList.contains(guess)) {
                boolean matched = Arrays.stream(winner).anyMatch(guess::equals);
                if (matched) {
                    total += 10;
                }
            }
            guessList.add(guess);
            ++i;
        }
        return total;
    }

    public static void main(String[] args) {
        int total = 0;
        Scanner scan = new Scanner(System.in);
        while (total != -1) {
            System.out.println("Enter the numbers on your ticket:");
            total = processUserEntries(total, scan);
            if (total != -1 && total != -2) {
                String response = formResponse(total);
                System.out.println(response);
                total = 0;
            }
            if (total == -2) {
                total = 0;
            }
        }
        System.out.println("Thank you for using the Lottery Calculator.");
    }
}
