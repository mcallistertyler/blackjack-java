package com.cardgame.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Player {
    private String name;
    private final List<Card> heldCards = new ArrayList<>();

    public Player(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHeldCards() {
        return heldCards;
    }

    public String viewHand() {
        StringJoiner joiner = new StringJoiner(",");
        getHeldCards().forEach(card -> {
            joiner.add(card.getFullCard());
        });
        return joiner.toString();
    }

    public int getCardTotal() {
      if (heldCards.isEmpty()) {
          return 0;
      } else {
         return heldCards.stream().mapToInt(Card::getCardValue).sum();
      }
    }

    public List<Card> drawFromDeck(Deck deck, int numberToDraw) {
        for (int i = 0; i < numberToDraw; i++) {
            heldCards.add(deck.drawCard());
        }
        return heldCards;
    }

    public String getName() {
        return this.name;
    }
}
