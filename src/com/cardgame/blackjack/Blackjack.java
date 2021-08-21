package com.cardgame.blackjack;


import java.util.List;

import static com.cardgame.blackjack.DeckFileReader.readDeckFile;

final class Blackjack {
    public static void main(String[] args) {
        String filePath = "";
        boolean shuffleCheck = true;
        if (args.length > 0) {
           filePath = args[0];
        }
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("false")) {
                shuffleCheck = false;
                System.out.println("Shuffling turned off. Using deck as provided.");
            }
        }
        List<String> deckFile = readDeckFile(filePath);
        Deck deck = new Deck(deckFile);
        Player sam = new Player("sam");
        Player dealer = new Player("dealer");
        GameState blackjack = new GameState(sam, dealer, deck, shuffleCheck);
        blackjack.startGame();
    }

}
