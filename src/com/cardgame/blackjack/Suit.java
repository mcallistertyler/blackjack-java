package com.cardgame.blackjack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    static final Map<String, Suit> suitFromString = new HashMap<>();

    static {
        List<String> valueList = Arrays.asList("C", "D", "H", "S");
        Suit[] validValues = Suit.values();
        int currentValue = 0;
        for (String stringValue: valueList) {
            suitFromString.put(stringValue, validValues[currentValue]);
            currentValue++;
        }
    }

    public String getShortName() {
        return name().substring(0, 1);
    }

}
