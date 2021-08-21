package com.cardgame.blackjack;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.cardgame.blackjack.DeckFileReader.readDeckFile;
import static com.cardgame.blackjack.DeckFileReader.validCardList;
import static com.cardgame.blackjack.DeckFileReader.validateInput;

public class DeckFileReaderTest {

    @Test
    @DisplayName("Reads file successfully with 52 items.")
    void readTestFile() {
        List<String> cardList = readDeckFile("example-decks/shuffled-deck-of-cards.txt");
        Assertions.assertEquals(cardList.size(), 52);
    }

    @Test
    @DisplayName("Deck file must have 52 cards exactly.")
    void validateTestFileSize() {
        List<String> cardList = readDeckFile("example-decks/invalid-decks/deck-of-51-cards.txt");
        Assertions.assertEquals(cardList.size(), 0);
    }

    @Test
    @DisplayName("Validates duplicate values in input.")
    void validateTestFileDuplicates() {
        List<String> cardList = readDeckFile("example-decks/invalid-decks/deck-of-duplicate-cards.txt");
        Assertions.assertEquals(cardList.size(), 0);
    }

    @Test
    @DisplayName("Validates invalid values in input.")
    void validateInvalidCardValues() {
        List<String> cardList = readDeckFile("example-decks/invalid-decks/deck-of-invalid-card-values.txt");
        Assertions.assertEquals(cardList.size(), 0);
    }

    @Test
    @DisplayName("Returns empty list on failure.")
    void returnEmptyOnFailure() {
        List<String> emptyCardList = readDeckFile("");
        Assertions.assertTrue(emptyCardList.isEmpty());
    }

    @Test
    @DisplayName("Validation throws exception if card list does not contain 52 cards")
    void validationThrowsExceptionIfNotFiftyTwoCards() {
        List<String> deckMissingOneCard = validCardList();
        deckMissingOneCard.remove(1);
        Assertions.assertThrows(DeckFileReaderException.DeckFileReaderRequiresFiftyTwoCards.class, () ->
                validateInput(deckMissingOneCard));
    }

    @Test
    @DisplayName("Validation throws exception if card list contains duplicates")
    void validationThrowsExceptionIfContainsDuplicates() {
        List<String> deckDuplicateCard = validCardList();
        deckDuplicateCard.remove(1);
        deckDuplicateCard.add("SA");
        Assertions.assertThrows(DeckFileReaderException.DeckFileReaderDuplicateCards.class, () ->
                validateInput(deckDuplicateCard));
    }

    @Test
    @DisplayName("Validation throws exception if card list contains disallowed values")
    void validationThrowsExceptionIfIllegalValue() {
        List<String> deckMalformedCard = validCardList();
        deckMalformedCard.remove(1);
        deckMalformedCard.add("NOPE");
        Assertions.assertThrows(DeckFileReaderException.DeckFileReaderMalformedValues.class, () ->
                validateInput(deckMalformedCard));
    }
}
