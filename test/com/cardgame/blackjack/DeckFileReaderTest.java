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
        List<String> cardList = readDeckFile("deck-of-cards.txt");
        Assertions.assertEquals(cardList.size(), 52);
    }

    @Test
    @DisplayName("Validates size of input.")
    void validateTestFileSize() {
        List<String> cardList = readDeckFile("invalid-deck-of-cards.txt");
    }

    @Test
    @DisplayName("Validates duplicate values in input.")
    void validateTestFileDuplicates() {
        List<String> cardList = readDeckFile("invalid-deck-of-cards.txt");
    }

    @Test
    @DisplayName("Returns empty list on failure.")
    void returnEmptyOnFailure() {
        List<String> emptyCardList = readDeckFile("");
        Assertions.assertTrue(emptyCardList.isEmpty());
    }
}
