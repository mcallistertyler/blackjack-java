package com.cardgame.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private Stack<Card> cardStack = new Stack<>();

    public Deck() {
        List<String> emptyList = new ArrayList<>();
        initializeDeck(emptyList);
    }
    public Deck(List<String> readCards) {
        initializeDeck(readCards);
    }
    public Deck(Stack<Card> providedDeck) {
        this.cardStack = providedDeck;
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cardStack);
    }

    public Card drawCard() {
        if (cardStack.empty()) {
            System.out.println("The game should have ended by this point. But if this happens the game should end.");
        }
        return cardStack.pop();
    }

    public int deckSize() {
        return cardStack.size();
    }

    public void printDeck() {
        cardStack.forEach((card -> {
            System.out.println(card.getSuit() + " " + card.getCardValue());
        }));
    }

    public Stack<Card> getCardStack() {
        return cardStack;
    }

    private void createDeck() {
        List<Suit> suitValues = Arrays.asList(Suit.values());
        List<CardValue> cardValues = Arrays.asList(CardValue.values());
        suitValues.forEach(suit -> {
            cardValues.forEach(cardValue -> {
                cardStack.push(new Card(suit, cardValue));
            });
        });
        shuffleDeck();
    }

    private void initializeDeck(List<String> readCards) {
        if (readCards.isEmpty()) {
            System.out.println("No deck file provided. Building new deck from scratch");
            createDeck();
        } else {
            readCards.forEach((stringCard) -> {
                cardStack.push(new Card (stringCard));
            });
        }
    }
}
