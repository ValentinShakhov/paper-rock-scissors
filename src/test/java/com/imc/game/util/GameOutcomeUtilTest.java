package com.imc.game.util;

import com.imc.game.entity.GameOutcome;
import com.imc.game.entity.RoundOutcome;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GameOutcomeUtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailNullArg() {
        new GameOutcomeUtil().getGameOutcome(null);
    }

    @Test
    public void shouldReturnWin() {
        final Map<RoundOutcome, Long> roundOutcomesMap = new HashMap<>();

        roundOutcomesMap.put(RoundOutcome.WIN, 1L);

        final GameOutcome gameOutcome = new GameOutcomeUtil().getGameOutcome(roundOutcomesMap);

        assertEquals(gameOutcome, GameOutcome.WIN);
    }

    @Test
    public void shouldReturnLoose() {
        final Map<RoundOutcome, Long> roundOutcomesMap = new HashMap<>();

        roundOutcomesMap.put(RoundOutcome.LOOSE, 1L);

        final GameOutcome gameOutcome = new GameOutcomeUtil().getGameOutcome(roundOutcomesMap);

        assertEquals(gameOutcome, GameOutcome.LOOSE);
    }

    @Test
    public void shouldReturnTie() {
        final Map<RoundOutcome, Long> roundOutcomesMap = new HashMap<>();

        roundOutcomesMap.put(RoundOutcome.WIN, 1L);
        roundOutcomesMap.put(RoundOutcome.LOOSE, 1L);

        final GameOutcome gameOutcome = new GameOutcomeUtil().getGameOutcome(roundOutcomesMap);

        assertEquals(gameOutcome, GameOutcome.TIE);
    }
}