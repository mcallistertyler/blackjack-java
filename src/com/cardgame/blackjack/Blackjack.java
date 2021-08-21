package com.cardgame.blackjack;


import java.util.List;

import static com.cardgame.blackjack.DeckFileReader.readDeckFile;

final class Blackjack {
    public static void main(String[] args) {
        List<String> deckFile = readDeckFile("");
        Deck deck = new Deck(deckFile);
        Player sam = new Player("sam");
        Player dealer = new Player("dealer");
        GameState blackjack = new GameState(sam, dealer, deck, true);
        blackjack.startGame();
    }

}
