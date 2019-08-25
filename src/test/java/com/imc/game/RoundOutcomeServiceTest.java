package com.imc.game;

import com.imc.game.entity.Gesture;
import com.imc.game.entity.RoundOutcome;
import com.imc.game.service.GestureService;
import com.imc.game.service.RoundOutcomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoundOutcomeServiceTest {

    @Mock
    private GestureService gestureService;

    @InjectMocks
    private RoundOutcomeService roundOutcomeService;

    @Mock
    private Gesture firstGesture;

    @Mock
    private Gesture secondGesture;

    @Test
    public void shouldReturnTie() {
        final RoundOutcome roundOutcome = roundOutcomeService.getRoundOutcome(firstGesture, firstGesture);

        assertEquals(roundOutcome, RoundOutcome.TIE);

        verify(gestureService, never()).beats(any(), any());
    }

    @Test
    public void shouldReturnWin() {
        when(gestureService.beats(firstGesture, secondGesture)).thenReturn(true);

        final RoundOutcome roundOutcome = roundOutcomeService.getRoundOutcome(firstGesture, secondGesture);

        assertEquals(roundOutcome, RoundOutcome.WIN);

        verify(gestureService).beats(firstGesture, secondGesture);
    }

    @Test
    public void shouldReturnLoose() {
        when(gestureService.beats(firstGesture, secondGesture)).thenReturn(false);

        final RoundOutcome roundOutcome = roundOutcomeService.getRoundOutcome(firstGesture, secondGesture);

        assertEquals(roundOutcome, RoundOutcome.LOOSE);

        verify(gestureService).beats(firstGesture, secondGesture);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFirstNullArg() {
        roundOutcomeService.getRoundOutcome(null, secondGesture);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailSecondNullArg() {
        roundOutcomeService.getRoundOutcome(firstGesture, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailNullArgs() {
        roundOutcomeService.getRoundOutcome(null, null);
    }
}