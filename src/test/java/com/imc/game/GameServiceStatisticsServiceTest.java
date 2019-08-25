package com.imc.game;

import com.imc.game.entity.GameOutcome;
import com.imc.game.entity.GameStatistics;
import com.imc.game.entity.RoundOutcome;
import com.imc.game.entity.RoundResult;
import com.imc.game.service.GameOutcomeService;
import com.imc.game.service.GameStatisticsService;
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
public class GameServiceStatisticsServiceTest {

    @Mock
    private GameOutcomeService gameOutcomeService;

    @InjectMocks
    private GameStatisticsService gameStatisticsService;

    @Mock
    private RoundResult roundResult;

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailNullArg() {
        gameStatisticsService.getGameStatistics(null);
    }

    @Test
    public void shouldCreateGameStatistics() {
        final GameOutcome gameOutcome = GameOutcome.WIN;
        final ArrayList<RoundResult> roundResults = new ArrayList<>();

        roundResults.add(roundResult);

        when(roundResult.getRoundOutcome()).thenReturn(RoundOutcome.WIN);
        when(gameOutcomeService.getGameOutcome(anyMap())).thenReturn(gameOutcome);

        final GameStatistics gameStatistics = gameStatisticsService.getGameStatistics(roundResults);

        assertEquals(gameStatistics.getGameOutcome(), gameOutcome.toString());
        assertFalse(gameStatistics.getOverallStatistics().isEmpty());

        verify(gameOutcomeService).getGameOutcome(anyMap());
    }
}