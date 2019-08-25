package com.imc.game;

import com.imc.game.entity.RoundResult;
import com.imc.game.util.DisplayMessageUtil;
import com.imc.game.util.GestureUtil;
import com.imc.game.util.RoundOutcomeUtil;
import com.imc.game.util.UserInteractionUtil;
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
public class GameTest {

    @Mock
    private RoundOutcomeUtil roundOutcomeUtil;

    @Mock
    private UserInteractionUtil userInteractionUtil;

    @Mock
    private GestureUtil gestureUtil;

    @Mock
    private DisplayMessageUtil displayMessageUtil;

    @InjectMocks
    private Game game;

    @Test
    public void shouldPlayOnPositiveAmountOfRounds() {
        final int rounds = 10;

        final List<RoundResult> roundResults = game.playGame(rounds);

        assertEquals(rounds, roundResults.size());

        verify(displayMessageUtil).displayWelcomeMessage();
        verify(displayMessageUtil, times(rounds)).displayRoundNumber(anyLong());
        verify(displayMessageUtil, times(rounds)).displayRoundResult(any());

        verify(userInteractionUtil, times(rounds)).getUserGesture();
        verify(gestureUtil, times(rounds)).getRandom();
        verify(roundOutcomeUtil, times(rounds)).getRoundOutcome(any(), any());
    }

    @Test
    public void shouldNotPlayOnNonPositiveAmountOfRounds() {
        final int rounds = -10;

        final List<RoundResult> roundResults = game.playGame(rounds);

        assertTrue(roundResults.isEmpty());

        verify(displayMessageUtil).displayWelcomeMessage();
        verify(displayMessageUtil, never()).displayRoundNumber(anyLong());
        verify(displayMessageUtil, never()).displayRoundResult(any());

        verify(userInteractionUtil, never()).getUserGesture();
        verify(gestureUtil, never()).getRandom();
        verify(roundOutcomeUtil, never()).getRoundOutcome(any(), any());
    }
}