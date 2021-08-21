package com.cardgame.blackjack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardValueTest {

    @Test
    @DisplayName("Correctly maps strings to enum value.")
    void correctlyMapStringToEnum() {
        Assertions.assertEquals(CardValue.valueFromString.get("2"), CardValue.TWO);
        Assertions.assertEquals(CardValue.valueFromString.get("A"), CardValue.ACE);
        Assertions.assertEquals(CardValue.valueFromString.get("K"), CardValue.KING);
    }
    @Test
    @DisplayName("Correctly maps enum value back to string.")
    void correctlyMapEnumToString() {
        Assertions.assertEquals(CardValue.stringFromValue.get(CardValue.ACE), "A");
        Assertions.assertEquals(CardValue.stringFromValue.get(CardValue.TWO), "2");
        Assertions.assertEquals(CardValue.stringFromValue.get(CardValue.KING), "K");
    }

    @Test
    @DisplayName("Can get card value.")
    void canGetCardValue() {
        Assertions.assertEquals(CardValue.KING.getValue(), 10);
        Assertions.assertEquals(CardValue.ACE.getValue(), 11);
        Assertions.assertEquals(CardValue.TWO.getValue(),2);
    }

}
