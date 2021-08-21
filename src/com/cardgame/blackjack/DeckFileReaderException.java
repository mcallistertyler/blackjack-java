package com.cardgame.blackjack;

abstract class DeckFileReaderException extends Exception{
    DeckFileReaderException(String message) {
        super(message);
    }

    static class DeckFileReaderDuplicateCards extends DeckFileReaderException {
        public DeckFileReaderDuplicateCards() {
            super("Duplicate cards detected in deck file so a new deck will be generated.");
        }
    }

    static class DeckFileReaderMalformedValues extends DeckFileReaderException {
        public DeckFileReaderMalformedValues() {
            super("Invalid values detected in deck file so a new deck will be generated.");
        }
    }

    static class DeckFileReaderRequiresFiftyTwoCards extends DeckFileReaderException {
        public DeckFileReaderRequiresFiftyTwoCards() {
            super("A deck file requires exactly 52 unique cards, a new deck will be generated.");
        }
    }

}
