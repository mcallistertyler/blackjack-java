package com.cardgame.blackjack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void canSetPlayerName() {
        Player player = new Player("Tyler");
        Assertions.assertEquals(player.getName(), "Tyler");
    }
}
