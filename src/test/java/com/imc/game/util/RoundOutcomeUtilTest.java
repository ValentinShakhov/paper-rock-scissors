package com.imc.game.util;

import com.imc.game.entity.Gesture;
import com.imc.game.entity.RoundOutcome;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoundOutcomeUtilTest {

    @Mock
    private GestureUtil gestureUtil;

    @InjectMocks
    private RoundOutcomeUtil roundOutcomeUtil;

    @Mock
    private Gesture firstGesture;

    @Mock
    private Gesture secondGesture;

    @Test
    public void shouldReturnTie() {
        final RoundOutcome roundOutcome = roundOutcomeUtil.getRoundOutcome(firstGesture, firstGesture);

        assertEquals(roundOutcome, RoundOutcome.TIE);

        verify(gestureUtil, never()).beats(any(), any());
    }

    @Test
    public void shouldReturnWin() {
        when(gestureUtil.beats(firstGesture, secondGesture)).thenReturn(true);

        final RoundOutcome roundOutcome = roundOutcomeUtil.getRoundOutcome(firstGesture, secondGesture);

        assertEquals(roundOutcome, RoundOutcome.WIN);

        verify(gestureUtil).beats(firstGesture, secondGesture);
    }

    @Test
    public void shouldReturnLoose() {
        when(gestureUtil.beats(firstGesture, secondGesture)).thenReturn(false);

        final RoundOutcome roundOutcome = roundOutcomeUtil.getRoundOutcome(firstGesture, secondGesture);

        assertEquals(roundOutcome, RoundOutcome.LOOSE);

        verify(gestureUtil).beats(firstGesture, secondGesture);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFirstNullArg() {
        roundOutcomeUtil.getRoundOutcome(null, secondGesture);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailSecondNullArg() {
        roundOutcomeUtil.getRoundOutcome(firstGesture, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailNullArgs() {
        roundOutcomeUtil.getRoundOutcome(null, null);
    }
}