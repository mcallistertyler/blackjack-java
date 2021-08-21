package com.cardgame.blackjack;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.cardgame.blackjack.DeckFileReader.readDeckFile;

public class DeckFileReaderTest {

    @Test
    @DisplayName("Reads file successfully with 52 items.")
    void readTestFile() {
        List<String> cardList = readDeckFile("example-decks/deck-of-cards.txt");
        Assertions.assertEquals(cardList.size(), 52);
    }

    @Test
    @DisplayName("Deck file must have 52 cards exactly.")
    void validateTestFileSize() {
        List<String> cardList = readDeckFile("example-decks/deck-of-51-cards.txt");
        Assertions.assertEquals(cardList.size(), 0);
    }

    @Test
    @DisplayName("Validates duplicate values in input.")
    void validateTestFileDuplicates() {
        List<String> cardList = readDeckFile("example-decks/deck-of-duplicate-cards.txt");
        Assertions.assertEquals(cardList.size(), 0);
    }

    @Test
    @DisplayName("Validates invalid values in input.")
    void validateInvalidCardValues() {
        List<String> cardList = readDeckFile("example-decks/deck-of-invalid-card-values.txt");
        Assertions.assertEquals(cardList.size(), 0);
    }

    @Test
    @DisplayName("Returns empty list on failure.")
    void returnEmptyOnFailure() {
        List<String> emptyCardList = readDeckFile("");
        Assertions.assertTrue(emptyCardList.isEmpty());
    }
}
