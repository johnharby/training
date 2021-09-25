package com.interview;

// Java program for checking
// balanced Parenthesis
import java.util.*;

public class BalancedParen {

    // function to check if parantheses are balanced
    static boolean areParanthesesBalanced(String expr)
    {
        // Using ArrayDeque is faster than using Stack class
        Deque<Character> stack = new ArrayDeque<Character>();

        // Traversing the Expression
        for (int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);

            if (x == '{') {
                // Push the element in the stack
                stack.push(x);
                continue;
            }

            // IF current current character is not opening
            // bracket, then it must be closing. So stack
            // cannot be empty at this point.
            if (stack.isEmpty())
                return false;

            // When } is found pop one { off the stack
            if (x == '}') {
                stack.pop();
            }

        }
        // Check Empty Stack
        return (stack.isEmpty());
    }

    /*driver program to test above functions*/
    public static void main(String[] args)
    {
        String expr = "{{}}";
        if (areParanthesesBalanced(expr))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");

        String expr2 = "{{{{{}}}}}}";
        if (areParanthesesBalanced(expr2))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");
    }
}

