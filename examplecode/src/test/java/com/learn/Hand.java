package com.learn;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Deck.Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Deck.Card> cards) {
        this.cards = cards;
    }

    public void dealCard(Deck.Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("Card already in hand-misdeal");
        }
        cards.add(card);
    }

    public Deck.Card play(Deck.Card card) {
        if (!cards.contains(card)) {
            throw new IllegalArgumentException("Call to play-card not in hand");
        }
        cards.remove(card);
        return card;
    }
    public Deck.Card findHighestForSuit(Deck.SUIT suit) {
        int maxRank = 0;
        String strRank = "";
        for (Deck.Card card : cards) {
            if (card.suit.equals(suit)) {
                int rnk = card.getIntRank();
                if (rnk > maxRank) {
                    maxRank = rnk;
                    strRank = card.rank;
                }
            }
        }
        return Deck.createCard(strRank, suit);
    }
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (Deck.Card card : cards) {
            ret.append(card.rank + " of " + card.suit + "\n");
        }
        return ret.toString();
    }
}
