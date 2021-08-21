package com.cardgame.blackjack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CardValue {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10),
        ACE(11);

        private final int value;

        static final Map<String, CardValue> valueFromString = new HashMap<>();
        static final Map<CardValue, String> stringFromValue = new HashMap<>();

        static {
                List<String> valueList = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
                List<CardValue> validCardValues = Arrays.asList(CardValue.values());
                int currentValue = 0;
                for (String stringValue: valueList) {
                        valueFromString.put(stringValue, validCardValues.get(currentValue));
                        currentValue++;
                }
                currentValue = 0;
                for (CardValue cardValue : validCardValues) {
                        stringFromValue.put(cardValue, valueList.get(currentValue));
                        currentValue++;
                }
        }

        CardValue(final int cardValue) {
                value = cardValue;
        }
        public int getValue() {
                return value;
        }
        public String getShortName() {
            return stringFromValue.get(this);
        }

}
