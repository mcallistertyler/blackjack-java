package com.cardgame.blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DeckFileReader {
    public static List<String> readDeckFile(String filePath) {
        List<String> cardList = new ArrayList<>();
        if (filePath == null) {
            return cardList;
        }
        try {
            File deckFile = new File(filePath);
            Scanner reader = new Scanner(deckFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                cardList = Arrays.asList(data.split("\\s*,\\s*"));
            }
            reader.close();
            return cardList;
        } catch (FileNotFoundException e) {
            System.out.println("No deck file found, or incorrect number or type of cards within file.");
            return cardList;
        }
    }
}
