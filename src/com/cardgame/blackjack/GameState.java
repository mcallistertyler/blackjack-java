package com.cardgame.blackjack;

import java.util.StringJoiner;

public class GameState {
    private final Player gambler;
    private final Player dealer;
    private final Deck deck;
    private final boolean shuffleDeck;
    private String winner;
    private String winnerHand;

    public GameState(Player gambler, Player dealer, Deck deck, boolean shuffleDeck) {
        this.gambler = gambler;
        this.dealer = dealer;
        this.deck = deck;
        this.shuffleDeck = shuffleDeck;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
    public void setWinnerHand(String winnerHand) {
        this.winnerHand = winnerHand;
    }
    public String getWinner() {
        return this.winner;
    }
    public String getWinningHand() {
        return this.winnerHand;
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
        String winningHand = winner.getName() + ": " + winner.viewHand();
        String losingHand = loser.getName() + ": " + loser.viewHand();
        System.out.println(winningHand);
        System.out.println(losingHand);
        this.setWinner(winner.getName());
        this.setWinnerHand(winningHand);
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
