package com.cardgame.blackjack;


import java.util.Objects;

public class Card {
    private final Suit suit;
    private final CardValue cardValue;

    public Card(String card) {
        String suitSubstring = card.substring(0,1);
        String valueSubstring = card.substring(1);
        cardValue = CardValue.valueFromString.get(valueSubstring);
        suit = Suit.suitFromString.get(suitSubstring);
    }

    public Card(Suit suit, CardValue cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    public int getCardValue() {
        return this.cardValue.getValue();
    }

    public String getFullCard() {
        return this.suit.getShortName() + this.cardValue.getShortName();
    }

    public Suit getSuit() {
        return this.suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && cardValue == card.cardValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, cardValue);
    }
}
