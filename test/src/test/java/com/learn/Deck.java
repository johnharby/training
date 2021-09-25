package com.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    public enum SUIT {
        CLUBS, DIAMONDS, HEARTS, SPADES;

        public static SUIT getFromInt(int i) {
            switch(i) {
                case 0:
                    return CLUBS;
                case 1:
                    return DIAMONDS;
                case 2:
                    return HEARTS;
                case 3:
                    return SPADES;
            }
            return null;
        }
    }

    public static final String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"
    };

    private final int n = SUIT.values().length + RANKS.length;
    private Card[] cards;

    public Deck() {
        // initialize deck
        int n = SUIT.values().length * RANKS.length;
        cards = new Card[n];
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = 0; j < SUIT.values().length; j++) {
                Card card = new Card(RANKS[i], SUIT.getFromInt(j));
                cards[SUIT.values().length*i + j] = card;
            }
        }
    }

    public void shuffle() {
        // shuffle
        cards = new Card[n];
        for (int i = 0; i < size(); i++) {
            int r = i + (int) (Math.random() * (n-i));
            Card temp = cards[r];
            cards[r] = cards[i];
            cards[i] = temp;
        }
    }

    public int size() {
        return cards.length;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(Arrays.asList(cards));
    }

    public static Card createCard(String rank, SUIT suit) {
        return new Card(rank, suit);
    }

    static class Card {
        SUIT suit;
        String rank;

        Card(String rank, SUIT suit) {
            this.suit = suit;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return rank + " of " + suit;
        }

        public int getIntRank() {
            ArrayList<String> rlist = new ArrayList<String>(
                    Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10")
            );
            if (rlist.contains(rank)) {
                return Integer.parseInt(rank);
            }
            switch(rank) {
                case "Jack":
                    return 11;
                case "Queen":
                    return 12;
                case "King":
                    return 13;
                case "Ace":
                    return 14;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
//        System.out.println("DECK 1:");
        deck.shuffle();
//        deck.getCards().stream().forEach(System.out::println);
//        System.out.println("\nDECK 2:");
//        deck.shuffle();
//        deck.getCards().stream().forEach(System.out::println);
        Hand hand = new Hand();
        for (int i = 0; i < 13; ++i) {
            hand.dealCard(deck.getCards().get(i));
        }
        System.out.println(hand.toString());
    }
}