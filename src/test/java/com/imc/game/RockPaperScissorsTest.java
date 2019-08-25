package com.imc.game;

import com.imc.game.entity.GameStatistics;
import com.imc.game.entity.RoundResult;
import com.imc.game.util.DisplayMessageUtil;
import com.imc.game.util.GameStatisticsUtil;
import com.imc.game.util.UserInteractionUtil;
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
public class RockPaperScissorsTest {

    @Mock
    private Game game;

    @Mock
    private UserInteractionUtil userInteractionUtil;

    @Mock
    private GameStatisticsUtil gameStatisticsUtil;

    @Mock
    private DisplayMessageUtil displayMessageUtil;

    @InjectMocks
    private RockPaperScissors rockPaperScissors;

    @Mock
    private GameStatistics gameStatistics;

    @Test
    public void shouldPlayGameAndDisplayResults() {
        final long numberOfRounds = 1L;
        final List<RoundResult> roundResults = Collections.emptyList();

        when(gameStatisticsUtil.getGameStatistics(anyList())).thenReturn(gameStatistics);
        when(userInteractionUtil.getNumberOfRounds()).thenReturn(numberOfRounds);
        when(game.playGame(numberOfRounds)).thenReturn(roundResults);

        rockPaperScissors.start();

        verify(userInteractionUtil).getNumberOfRounds();
        verify(game).playGame(numberOfRounds);
        verify(gameStatisticsUtil).getGameStatistics(roundResults);
        verify(displayMessageUtil).displayGameResults(any(), any(), any());
    }
}