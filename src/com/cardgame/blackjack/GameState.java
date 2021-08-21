package com.cardgame.blackjack;

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
        while (true) {

            if (shuffleDeck) {
                deck.shuffleDeck();
            }

            gambler.drawFromDeck(deck, 1);
            dealer.drawFromDeck(deck, 1);
            gambler.drawFromDeck(deck, 1);
            dealer.drawFromDeck(deck, 1);

            if (hitTwentyOne(gambler)) {
                declareWinner(gambler);
                break;
            }
            if (hitTwentyOne(dealer)) {
                declareWinner(dealer);
                break;
            }
            if (dealer.getCardTotal() == 22 && gambler.getCardTotal() == 22) {
                declareWinner(dealer);
                break;
            }
            drawUntil(gambler, 17);
            if (isBust(gambler)) {
                declareWinner(dealer);
                break;
            }
            drawUntil(dealer, gambler.getCardTotal());
            if (isBust(dealer)) {
                declareWinner(gambler);
                break;
            }
            if (gambler.getCardTotal() > dealer.getCardTotal()) {
               declareWinner(gambler);
               break;
            } else {
                declareWinner(dealer);
                break;
            }
        }
    }

    private void declareWinner(Player winner) {
        System.out.println(winner.getName());
        String gamblerHand = gambler.getName() + ": " + gambler.viewHand();
        String dealerHand = dealer.getName() + ": " + dealer.viewHand();
        System.out.println(gamblerHand);
        System.out.println(dealerHand);
        this.setWinner(winner.getName());
        this.setWinnerHand(winner.viewHand());
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
