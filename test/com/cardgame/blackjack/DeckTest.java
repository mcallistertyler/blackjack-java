package com.cardgame.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private List<String> exampleDeck() {
        List<String> suitList = Arrays.asList("C", "D", "S", "H");
        List<String> valueList = Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
        List<String> mockStringDeck = new ArrayList<>();
        suitList.forEach(suit -> {
            valueList.forEach(cardValue -> {
                mockStringDeck.add(suit + cardValue);
            });
        });
        return mockStringDeck;
    }

    private Deck createDeckFromEmptyList() {
       return new Deck();
    }

    @Test
    @DisplayName("Deck contains 52 cards.")
    void deckContainsFiftyTwo() {
        Deck deck = createDeckFromEmptyList();
        Assertions.assertEquals(deck.deckSize(), 52);
    }

    @Test
    @DisplayName("Every card in a deck should be unique.")
    void deckShouldContainNoDuplicates() {
        Deck deck = createDeckFromEmptyList();
        Stack<Card> generatedDeck = deck.getCardStack();
        Set<Card> setOfCards = new HashSet<>(generatedDeck);
        Assertions.assertEquals(generatedDeck.size(), setOfCards.size());
    }

    @Test
    @DisplayName("Drawing a card should remove that card from the deck.")
    void drawingCardShouldRemove() {
        Deck deck = createDeckFromEmptyList();
        Assertions.assertEquals(deck.deckSize(), 52);
        Card drawnCard = deck.drawCard();
        Assertions.assertEquals(deck.deckSize(), 51);
        List<Card> currentDeck = deck.getCardStack();
        currentDeck.forEach(card -> {
            Assertions.assertNotEquals(drawnCard, card);
        });
    }

    @Test
    @DisplayName("Can build a deck from list of string card values.")
    void buildDeckFromInput() {
        List<String> dummyDeck = exampleDeck();
        Deck deck = new Deck(dummyDeck);
        Assertions.assertEquals(deck.deckSize(), 52);
        Stack<Card> generatedDeck = deck.getCardStack();
        Set<Card> setOfCards = new HashSet<>(generatedDeck);
        Assertions.assertEquals(generatedDeck.size(), setOfCards.size());
    }
}
