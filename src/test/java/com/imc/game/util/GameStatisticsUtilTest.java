package com.imc.game.util;

import com.imc.game.entity.GameOutcome;
import com.imc.game.entity.GameStatistics;
import com.imc.game.entity.RoundOutcome;
import com.imc.game.entity.RoundResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameStatisticsUtilTest {

    @Mock
    private GameOutcomeUtil gameOutcomeUtil;

    @InjectMocks
    private GameStatisticsUtil gameStatisticsUtil;

    @Mock
    private RoundResult roundResult;

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailNullArg() {
        gameStatisticsUtil.getGameStatistics(null);
    }

    @Test
    public void shouldCreateGameStatistics() {
        final GameOutcome gameOutcome = GameOutcome.WIN;
        final ArrayList<RoundResult> roundResults = new ArrayList<>();

        roundResults.add(roundResult);

        when(roundResult.getRoundOutcome()).thenReturn(RoundOutcome.WIN);
        when(gameOutcomeUtil.getGameOutcome(anyMap())).thenReturn(gameOutcome);

        final GameStatistics gameStatistics = gameStatisticsUtil.getGameStatistics(roundResults);

        assertEquals(gameStatistics.getGameOutcome(), gameOutcome.toString());
        assertFalse(gameStatistics.getOverallStatistics().isEmpty());

        verify(gameOutcomeUtil).getGameOutcome(anyMap());
    }
}