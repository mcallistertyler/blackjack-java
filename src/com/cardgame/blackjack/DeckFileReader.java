package com.cardgame.blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class DeckFileReader {
    public static List<String> readDeckFile(String filePath) {
        List<String> cardList = new ArrayList<>();
        try {
            File deckFile = new File(filePath);
            Scanner reader = new Scanner(deckFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                cardList = Arrays.asList(data.split("\\s*,\\s*"));
            }
            reader.close();
            try {
                validateInput(cardList);
                return cardList;
            } catch (DeckFileReaderException d) {
                System.out.println(d.getMessage());
                return new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file provided so a deck will be generated");
            return cardList;
        }
    }

    public static void validateInput(List<String> cardList) throws DeckFileReaderException {
        if (cardList.size() != 52) {
            throw new DeckFileReaderException.DeckFileReaderRequiresFiftyTwoCards();
        }
        Set<String> cardSet = new HashSet<>(cardList);
        if (cardSet.size() < cardList.size()) {
           throw new DeckFileReaderException.DeckFileReaderDuplicateCards();
        }
        List<String> sortedCardList = cardList.stream().sorted().collect(Collectors.toList());
        List<String> sortedValidValues = validCardList().stream().sorted().collect(Collectors.toList());

        if (!sortedValidValues.equals(sortedCardList)) {
            throw new DeckFileReaderException.DeckFileReaderMalformedValues();
        }
    }

    public static List<String> validCardList() {
        List<String> validSuits = Arrays.asList("D", "H", "S", "C");
        List<String> validValues = Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
        return validSuits.stream().flatMap(suit -> validValues.stream().map(value -> suit + value)).collect(Collectors.toList());
    }

}
