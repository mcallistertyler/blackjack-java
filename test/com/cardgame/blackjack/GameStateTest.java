package com.cardgame.blackjack;

import java.util.Collections;
import java.util.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameStateTest {
    private final Player dummyPlayer = new Player("sam");
    private final Player dummyDealer = new Player("dealer");

    private Deck modifyTopCards(Card...cards) {
        Deck deck = new Deck();
        Stack<Card> modifiedDeck = deck.getCardStack();
        int cardCounter = 0;
        int deckSize = modifiedDeck.size();
        for (Card card : cards) {
            int cardIndex = modifiedDeck.indexOf(card);
            Collections.swap(modifiedDeck, (deckSize - 1) - cardCounter, cardIndex);
            cardCounter++;
        }
        return new Deck(modifiedDeck);
    }

    @Test
    @DisplayName("Sam wins if he draws 21.")
    void playerWinsIfDrawsTwentyOne() {
        Card aceDiamonds = new Card(Suit.DIAMONDS, CardValue.ACE);
        Card twoDiamonds = new Card(Suit.DIAMONDS, CardValue.TWO);
        Card aceHearts = new Card(Suit.HEARTS, CardValue.TEN);
        Card twoHearts = new Card(Suit.HEARTS, CardValue.TWO);
        Deck winningDeck = modifyTopCards(aceDiamonds, twoDiamonds, aceHearts, twoHearts);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, winningDeck, false);
        blackjack.startGame();
        Assertions.assertEquals(blackjack.getWinner(), dummyPlayer.getName());
    }

    @Test
    @DisplayName("Dealer wins if they draw 21.")
    void dealerWinsIfDrawsTwentyOne() {
        Card twoDiamonds = new Card(Suit.DIAMONDS, CardValue.TWO);
        Card aceDiamonds = new Card(Suit.DIAMONDS, CardValue.ACE);
        Card twoHearts = new Card(Suit.HEARTS, CardValue.TWO);
        Card tenHearts = new Card(Suit.HEARTS, CardValue.TEN);
        Deck losingDeck = modifyTopCards(twoDiamonds, aceDiamonds, twoHearts, tenHearts);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, losingDeck, false);
        blackjack.startGame();
        Assertions.assertEquals(blackjack.getWinner(), dummyDealer.getName());
    }

    @Test
    @DisplayName("Sam wins if both players start with blackjack.")
    void samWinsIfBothBlackJack() {
        Card aceHearts = new Card(Suit.HEARTS, CardValue.ACE);
        Card aceDiamonds = new Card(Suit.DIAMONDS, CardValue.ACE);
        Card tenDiamonds = new Card(Suit.DIAMONDS, CardValue.TEN);
        Card tenHearts = new Card(Suit.HEARTS, CardValue.TEN);
        Deck winningDeck = modifyTopCards(aceHearts, aceDiamonds, tenDiamonds, tenHearts);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, winningDeck, false);
        blackjack.startGame();
        Assertions.assertEquals(blackjack.getWinner(), dummyPlayer.getName());
    }

}
