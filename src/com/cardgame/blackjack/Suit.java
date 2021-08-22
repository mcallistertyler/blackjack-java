package com.cardgame.blackjack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Suit {
    CLUBS("C"),
    DIAMONDS("D"),
    HEARTS("H"),
    SPADES("S");

    static final Map<String, Suit> suitFromString = new HashMap<>();
    private final String value;
    static {
        List<String> valueList = Arrays.asList("C", "D", "H", "S");
        List<Suit> suitValues = Arrays.asList(Suit.values());
        int currentValue = 0;
        for (String stringValue: valueList) {
            suitFromString.put(stringValue, suitValues.get(currentValue));
            currentValue++;
        }
    }

    Suit(final String suitValue) {
        value = suitValue;
    }
    public String getValue() {
        return value;
    }
}
