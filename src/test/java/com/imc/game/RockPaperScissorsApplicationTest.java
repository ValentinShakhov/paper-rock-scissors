package com.imc.game;

import com.imc.game.entity.GameStatistics;
import com.imc.game.entity.RoundResult;
import com.imc.game.service.GameService;
import com.imc.game.service.GameStatisticsService;
import com.imc.game.service.UserInputConsoleService;
import com.imc.game.service.UserMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RockPaperScissorsApplicationTest {

    @Mock
    private GameService gameService;

    @Mock
    private UserInputConsoleService userInputService;

    @Mock
    private GameStatisticsService gameStatisticsService;

    @Mock
    private UserMessageService userMessageService;

    @InjectMocks
    private RockPaperScissorsApplication rockPaperScissorsApplication;

    @Mock
    private GameStatistics gameStatistics;

    @Test
    public void shouldPlayGameAndDisplayResults() {
        final long numberOfRounds = 1L;
        final List<RoundResult> roundResults = Collections.emptyList();

        when(gameStatisticsService.getGameStatistics(anyList())).thenReturn(gameStatistics);
        when(userInputService.getNumberOfRounds()).thenReturn(numberOfRounds);
        when(gameService.playGame(numberOfRounds)).thenReturn(roundResults);

        rockPaperScissorsApplication.start();

        verify(userInputService).getNumberOfRounds();
        verify(gameService).playGame(numberOfRounds);
        verify(gameStatisticsService).getGameStatistics(roundResults);
        verify(userMessageService).displayGameResults(any(), any(), any());
    }
}