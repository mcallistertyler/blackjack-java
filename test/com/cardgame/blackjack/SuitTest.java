package com.cardgame.blackjack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuitTest {

    @Test
    @DisplayName("Correctly maps strings to enum value.")
    void correctlyMapStringToEnum() {
        Assertions.assertEquals(Suit.suitFromString.get("C"), Suit.CLUBS);
        Assertions.assertEquals(Suit.suitFromString.get("D"), Suit.DIAMONDS);
        Assertions.assertEquals(Suit.suitFromString.get("H"), Suit.HEARTS);
        Assertions.assertEquals(Suit.suitFromString.get("S"), Suit.SPADES);
    }

    @Test
    @DisplayName("Shortname returns singular string.")
    void shortNameReturnsSingularString() {
        Assertions.assertEquals(Suit.DIAMONDS.getValue(), "D");
        Assertions.assertEquals(Suit.HEARTS.getValue(), "H");
        Assertions.assertEquals(Suit.CLUBS.getValue(), "C");
        Assertions.assertEquals(Suit.SPADES.getValue(), "S");
    }
}
