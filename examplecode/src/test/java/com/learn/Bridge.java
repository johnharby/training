package com.learn;

import java.util.List;

public class Bridge {
    Hand[] hands;
    Deck deck;

    public Bridge() {
        hands = new Hand[4];
        for (int i = 0; i < 4; ++i) {
            hands[i] = new Hand();
        }
        deck = new Deck();
        deck.shuffle();
    }

    public void deal() {
        List<Deck.Card> cards = deck.getCards();
        for (int i = 0; i < cards.size(); ++i) {
            hands[i % 4].dealCard(cards.get(i));
        }
    }

    public Hand[] getHands() {
        return hands;
    }

    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        bridge.deal();
        for (Hand hand : bridge.getHands()) {
            System.out.println(hand.toString());
            System.out.println("\n\n");
        }
    }
}
