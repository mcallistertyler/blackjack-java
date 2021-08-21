package com.cardgame.blackjack;

import java.util.StringJoiner;

public class GameState {
    private final Player gambler;
    private final Player dealer;
    private final Deck deck;
    private final boolean shuffleDeck;

    public GameState(Player gambler, Player dealer, Deck deck, boolean shuffleDeck) {
        this.gambler = gambler;
        this.dealer = dealer;
        this.deck = deck;
        this.shuffleDeck = shuffleDeck;
    }

    public void startGame() {
        StringJoiner joiner = new StringJoiner(",");
        deck.getCardStack().forEach(card -> {
            joiner.add(card.getFullCard());
        });
        System.out.println(joiner.toString());

        while (true) {

            if (shuffleDeck) {
                deck.shuffleDeck();
            }

            gambler.drawFromDeck(deck, 1);
            dealer.drawFromDeck(deck, 1);
            gambler.drawFromDeck(deck, 1);
            dealer.drawFromDeck(deck, 1);

            if (hitTwentyOne(gambler)) {
                printGameResults(gambler, dealer);
                break;
            }
            if (hitTwentyOne(dealer)) {
                printGameResults(dealer, gambler);
                break;
            }
            if (dealer.getCardTotal() == 22 && gambler.getCardTotal() == 22) {
                printGameResults(dealer, gambler);
                break;
            }
            drawUntil(gambler, 17);
            if (isBust(gambler)) {
                printGameResults(dealer, gambler);
                break;
            }
            drawUntil(dealer, gambler.getCardTotal());
            if (isBust(dealer)) {
                printGameResults(gambler, dealer);
                break;
            }
            if (gambler.getCardTotal() > dealer.getCardTotal()) {
                printGameResults(gambler, dealer);
               break;
            } else {
                printGameResults(dealer, gambler);
                break;
            }
        }
    }

    private void printGameResults(Player winner, Player loser) {
        System.out.println(winner.getName());
        System.out.println(winner.getName() + ": " + winner.viewHand());
        System.out.println(loser.getName() + ": " + loser.viewHand());
    }

    private boolean hitTwentyOne(Player player) {
       return player.getCardTotal() == 21;
    }

    private void drawUntil(Player player, int until) {
        while (player.getCardTotal() < until) {
            player.drawFromDeck(deck, 1);
        }
    }

    private boolean isBust(Player player) {
        return player.getCardTotal() > 21;
    }
}
