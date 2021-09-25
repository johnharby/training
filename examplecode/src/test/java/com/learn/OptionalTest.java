package com.learn;

import org.junit.Test;
import java.util.Optional;

// From Java Code Geeks https://www.youtube.com/watch?v=G9JXMRZqmMg
public class OptionalTest {

    @Test
    public void testOptional() {
        Optional<String> message = Optional.empty();
        System.out.println("is present " + message.isPresent());
        String result = message.orElse("You have no new messages!");
        System.out.println(result);

        message = Optional.of("Remember to buy Milk and flour!");
        String otherResult = message.orElse("You have no new messages!");
        System.out.println(otherResult);
    }
}
