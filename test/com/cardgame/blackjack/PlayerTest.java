package com.cardgame.blackjack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.cardgame.blackjack.GameStateTest.modifyTopCards;

public class PlayerTest {
    @Test
    @DisplayName("Can set player name")
    void canSetPlayerName() {
        Player player = new Player("Tyler");
        Assertions.assertEquals(player.getName(), "Tyler");
    }

    @Test
    @DisplayName("Can draw cards from deck")
    void canDrawCardsFromDeck() {
        Player player = new Player("sam");
        Deck testDeck = new Deck();
        Assertions.assertEquals(player.getHeldCards().size(), 0);
        player.drawFromDeck(testDeck, 1);
        Assertions.assertEquals(player.getHeldCards().size(), 1);
    }

    @Test
    @DisplayName("Player has a card total depending on their held cards")
    void hasCardTotal() {
        Player player = new Player("sam");
        Card aceDiamonds = new Card(Suit.DIAMONDS, CardValue.ACE);
        Card twoSpades = new Card(Suit.SPADES, CardValue.TWO);
        Deck modifiedDeck = modifyTopCards(aceDiamonds, twoSpades);
        Assertions.assertEquals(player.getCardTotal(), 0);
        player.drawFromDeck(modifiedDeck, 2);
        Assertions.assertEquals(player.getCardTotal(), 13);
    }

}
