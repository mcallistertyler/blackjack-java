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
            if (validateInput(cardList)) {
                return cardList;
            } else {
                return new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            return cardList;
        }
    }

    private static boolean validateInput(List<String> cardList) {
        if (cardList.size() != 52) {
            System.out.println("52 cards are required to make a deck.");
            return false;
        }
        Set<String> cardSet = new HashSet<>(cardList);
        if (cardSet.size() < cardList.size()) {
           System.out.println("Duplicated values found in deck file.");
           return false;
        }
        List<String> sortedCardList = cardList.stream().sorted().collect(Collectors.toList());
        List<String> sortedValidValues = validStringValues().stream().sorted().collect(Collectors.toList());

        if (!sortedValidValues.equals(sortedCardList)) {
            System.out.println("Invalid values detected in deck file.");
            return false;
        }
        return true;
    }

    private static List<String> validStringValues() {
        List<String> validSuits = Arrays.asList("D", "H", "S", "C");
        List<String> validValues = Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
        List<String> validCards = new ArrayList<>();
        validSuits.forEach(suit -> validValues.forEach(value -> validCards.add(suit.concat(value))));
        return validCards;
    }

}
