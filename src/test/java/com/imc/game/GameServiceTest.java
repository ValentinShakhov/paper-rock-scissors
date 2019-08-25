package com.imc.game;

import com.imc.game.entity.RoundResult;
import com.imc.game.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private RoundOutcomeService roundOutcomeService;

    @Mock
    private UserInputService userInputService;

    @Mock
    private GestureService gestureService;

    @Mock
    private UserMessageService userMessageService;

    @InjectMocks
    private GameService gameService;

    @Test
    public void shouldPlayOnPositiveAmountOfRounds() {
        final int rounds = 10;

        final List<RoundResult> roundResults = gameService.playGame(rounds);

        assertEquals(rounds, roundResults.size());

        verify(userMessageService).displayWelcomeMessage();
        verify(userMessageService, times(rounds)).displayRoundNumber(anyLong());
        verify(userMessageService, times(rounds)).displayRoundResult(any());

        verify(userInputService, times(rounds)).getUserGesture();
        verify(gestureService, times(rounds)).getRandom();
        verify(roundOutcomeService, times(rounds)).getRoundOutcome(any(), any());
    }

    @Test
    public void shouldNotPlayOnNonPositiveAmountOfRounds() {
        final int rounds = -10;

        final List<RoundResult> roundResults = gameService.playGame(rounds);

        assertTrue(roundResults.isEmpty());

        verify(userMessageService).displayWelcomeMessage();
        verify(userMessageService, never()).displayRoundNumber(anyLong());
        verify(userMessageService, never()).displayRoundResult(any());

        verify(userInputService, never()).getUserGesture();
        verify(gestureService, never()).getRandom();
        verify(roundOutcomeService, never()).getRoundOutcome(any(), any());
    }
}