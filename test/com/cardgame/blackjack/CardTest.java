package com.cardgame.blackjack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("Can make card using string values")
    void canMakeCardUsingStringValues() {
        Card kingOfDiamonds = new Card("DK");
        Assertions.assertEquals(kingOfDiamonds.getSuit(), Suit.DIAMONDS);
        Assertions.assertEquals(kingOfDiamonds.getCardRank(), CardValue.KING);
        Assertions.assertEquals(kingOfDiamonds.getFullCard(), "DK");

        Card aceOfSpades = new Card("SA");
        Assertions.assertEquals(aceOfSpades.getSuit(), Suit.SPADES);
        Assertions.assertEquals(aceOfSpades.getCardRank(), CardValue.ACE);
        Assertions.assertEquals(aceOfSpades.getFullCard(), "SA");

        Card tenOfHearts = new Card("H10");
        Assertions.assertEquals(tenOfHearts.getSuit(), Suit.HEARTS);
        Assertions.assertEquals(tenOfHearts.getCardRank(), CardValue.TEN);
        Assertions.assertEquals(tenOfHearts.getFullCard(), "H10");

        Card twoOfClubs = new Card("C2");
        Assertions.assertEquals(twoOfClubs.getSuit(), Suit.CLUBS);
        Assertions.assertEquals(twoOfClubs.getCardRank(), CardValue.TWO);
        Assertions.assertEquals(twoOfClubs.getFullCard(), "C2");
    }

    @Test
    @DisplayName("Ace value is 11.")
    void aceValueIsEleven() {
        Card aceOfSpades = new Card("SA");
        Assertions.assertEquals(aceOfSpades.getCardValue(), 11);
    }

    @Test
    @DisplayName("Royalty card values are 10.")
    void royaltyValueIsTen() {
        Card kingOfSpades = new Card("SK");
        Card queenOfSpades = new Card("SQ");
        Card jackOfSpades = new Card("SJ");
        Assertions.assertEquals(kingOfSpades.getCardValue(), 10);
        Assertions.assertEquals(queenOfSpades.getCardValue(), 10);
        Assertions.assertEquals(jackOfSpades.getCardValue(), 10);
    }

    @Test
    @DisplayName("Can make card by passing suit and card value into constructor.")
    void createCardWithSuitAndCardValueParameters() {
        Card aceOfSpades = new Card(Suit.SPADES, CardValue.ACE);
        Assertions.assertEquals(aceOfSpades.getSuit(), Suit.SPADES);
        Assertions.assertEquals(aceOfSpades.getCardRank(), CardValue.ACE);
        Assertions.assertEquals(aceOfSpades.getFullCard(), "SA");
    }

}
