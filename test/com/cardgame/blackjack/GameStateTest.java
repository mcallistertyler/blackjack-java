package com.cardgame.blackjack;

import java.util.Collections;
import java.util.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameStateTest {
    private final Player dummyPlayer = new Player("sam");
    private final Player dummyDealer = new Player("dealer");

    public static Deck modifyTopCards(Card...cards) {
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

    @Test
    @DisplayName("Dealer wins if both players start with 22 (two aces).")
    void dealerWinIfBothTwentyTwo() {
        Card aceHearts = new Card(Suit.HEARTS, CardValue.ACE);
        Card aceDiamonds = new Card(Suit.DIAMONDS, CardValue.ACE);
        Card aceSpades = new Card(Suit.SPADES, CardValue.ACE);
        Card aceClubs = new Card(Suit.CLUBS, CardValue.ACE);
        Deck losingDeck = modifyTopCards(aceClubs, aceSpades, aceDiamonds, aceHearts);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, losingDeck, false);
        blackjack.startGame();
        Assertions.assertEquals(blackjack.getWinner(), dummyDealer.getName());
    }

    @Test
    @DisplayName("Sam starts drawing cards and stops once reaching 17 or higher.")
    void samStopsDrawingAtSeventeen() {
        Card tenHearts = new Card(Suit.HEARTS, CardValue.TEN);
        Card twoHearts = new Card(Suit.HEARTS, CardValue.TWO);
        Card fiveHearts = new Card(Suit.HEARTS, CardValue.FIVE);
        Card twoClubs = new Card(Suit.CLUBS, CardValue.TWO);
        Card twoDiamonds = new Card(Suit.DIAMONDS, CardValue.TWO);
        Deck testDeck = modifyTopCards(tenHearts, twoHearts, fiveHearts, twoClubs, twoDiamonds);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, testDeck, false);
        blackjack.startGame();
        Assertions.assertEquals(dummyPlayer.getCardTotal(), 17);
        Assertions.assertTrue(dummyPlayer.getHeldCards().size() > 2);
    }
    @Test
    @DisplayName("Sam loses if card total goes above 21.")
    void samLosesIfHandTotalMoreThanTwentyOne() {
        Card tenHearts = new Card(Suit.HEARTS, CardValue.TEN);
        Card twoHearts = new Card(Suit.HEARTS, CardValue.TWO);
        Card fiveHearts = new Card(Suit.HEARTS, CardValue.FIVE);
        Card twoClubs = new Card(Suit.CLUBS, CardValue.TWO);
        Card kingDiamond = new Card(Suit.DIAMONDS, CardValue.KING);
        Deck testDeck = modifyTopCards(tenHearts, twoHearts, fiveHearts, twoClubs, kingDiamond);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, testDeck, false);
        blackjack.startGame();
        Assertions.assertTrue(dummyPlayer.getCardTotal() > 21);
        Assertions.assertEquals(blackjack.getWinner(), dummyDealer.getName());
    }

    @Test
    @DisplayName("Dealer starts drawing once Sam finishes.")
    void dealerStartsDrawing() {
        Card tenHearts = new Card(Suit.HEARTS, CardValue.TEN);
        Card twoHearts = new Card(Suit.HEARTS, CardValue.TWO);
        Card fiveHearts = new Card(Suit.HEARTS, CardValue.FIVE);
        Card twoClubs = new Card(Suit.CLUBS, CardValue.TWO);
        Card twoDiamonds = new Card(Suit.DIAMONDS, CardValue.TWO);
        Card fiveSpades = new Card(Suit.SPADES, CardValue.FIVE);
        Card sixSpades = new Card(Suit.SPADES, CardValue.SIX);
        Card sixHearts = new Card(Suit.HEARTS, CardValue.SIX);
        Deck testDeck = modifyTopCards(tenHearts, twoHearts, fiveHearts, twoClubs, twoDiamonds, fiveSpades, sixSpades, sixHearts);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, testDeck, false);
        blackjack.startGame();
        Assertions.assertTrue(dummyPlayer.getCardTotal() > 2);
    }

    @Test
    @DisplayName("Dealer stops drawing once their card total is higher than Sams.")
    void dealerStopsAfterAbovePlayerCardTotal() {
        Card tenHearts = new Card(Suit.HEARTS, CardValue.TEN);
        Card twoHearts = new Card(Suit.HEARTS, CardValue.TWO);
        Card fiveHearts = new Card(Suit.HEARTS, CardValue.FIVE);
        Card twoClubs = new Card(Suit.CLUBS, CardValue.TWO);
        Card twoDiamonds = new Card(Suit.DIAMONDS, CardValue.TWO);
        Card fiveSpades = new Card(Suit.SPADES, CardValue.FIVE);
        Card nineClubs = new Card(Suit.CLUBS, CardValue.NINE);
        Deck testDeck = modifyTopCards(tenHearts, twoHearts, fiveHearts, twoClubs, twoDiamonds, fiveSpades, nineClubs);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, testDeck, false);
        blackjack.startGame();
        Assertions.assertTrue(dummyDealer.getCardTotal() > dummyPlayer.getCardTotal());
        Assertions.assertEquals(dummyDealer.getHeldCards().size(), 4);
    }

    @Test
    @DisplayName("Dealer loses if they have more than 21.")
    void dealerLosesWithMoreThanTwentyOne() {
        Card tenHearts = new Card(Suit.HEARTS, CardValue.TEN);
        Card twoHearts = new Card(Suit.HEARTS, CardValue.TWO);
        Card fiveHearts = new Card(Suit.HEARTS, CardValue.FIVE);
        Card twoClubs = new Card(Suit.CLUBS, CardValue.TWO);
        Card twoDiamonds = new Card(Suit.DIAMONDS, CardValue.TWO);
        Card queenDiamonds = new Card(Suit.DIAMONDS, CardValue.QUEEN);
        Card jackHearts = new Card(Suit.HEARTS, CardValue.JACK);
        Deck testDeck = modifyTopCards(tenHearts, twoHearts, fiveHearts, twoClubs, twoDiamonds, queenDiamonds, jackHearts);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, testDeck, false);
        blackjack.startGame();
        Assertions.assertEquals(blackjack.getWinner(), dummyPlayer.getName());
    }

    @Test
    @DisplayName("Sam wins if he has the highest card total.")
    void samWinsWithHighestValue(){
        Card tenHearts = new Card(Suit.HEARTS, CardValue.TEN);
        Card twoHearts = new Card(Suit.HEARTS, CardValue.TWO);
        Card kingSpades = new Card(Suit.SPADES, CardValue.KING);
        Card twoClubs = new Card(Suit.CLUBS, CardValue.TWO);
        Card queenDiamonds = new Card(Suit.DIAMONDS, CardValue.QUEEN);
        Card jackHearts = new Card(Suit.HEARTS, CardValue.JACK);
        Deck testDeck = modifyTopCards(tenHearts, twoHearts, kingSpades, twoClubs, queenDiamonds, jackHearts);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, testDeck, false);
        blackjack.startGame();
        Assertions.assertEquals(blackjack.getWinner(), dummyPlayer.getName());
    }

    @Test
    @DisplayName("Dealer wins with highest card total.")
    void dealerWinsWithHighestValue(){
        Card sevenHearts = new Card(Suit.HEARTS, CardValue.SEVEN);
        Card queenHearts = new Card(Suit.HEARTS, CardValue.QUEEN);
        Card kingSpades = new Card(Suit.SPADES, CardValue.KING);
        Card queenClubs = new Card(Suit.CLUBS, CardValue.QUEEN);
        Deck testDeck = modifyTopCards(sevenHearts, queenHearts, kingSpades, queenClubs);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, testDeck, false);
        blackjack.startGame();
        Assertions.assertEquals(blackjack.getWinner(), dummyDealer.getName());
    }

    @Test
    @DisplayName("Test from PDF.")
    void testFromPDF(){
        Card aceClubs = new Card(Suit.CLUBS, CardValue.ACE);
        Card fiveDiamonds = new Card(Suit.DIAMONDS, CardValue.FIVE);
        Card nineHearts = new Card(Suit.HEARTS, CardValue.NINE);
        Card queenHearts = new Card(Suit.HEARTS, CardValue.QUEEN);
        Card eightSpades = new Card(Suit.SPADES, CardValue.EIGHT);
        Deck testDeck = modifyTopCards(aceClubs, fiveDiamonds, nineHearts, queenHearts, eightSpades);
        GameState blackjack = new GameState(dummyPlayer, dummyDealer, testDeck, false);
        blackjack.startGame();
        Assertions.assertEquals(blackjack.getWinner(), dummyPlayer.getName());
    }

}
