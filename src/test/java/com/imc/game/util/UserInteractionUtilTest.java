package com.imc.game.util;

import com.imc.game.entity.Gesture;
import com.imc.game.exception.InvalidGestureKeyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserInteractionUtilTest {

    @Mock
    private GestureUtil gestureUtil;

    @Mock
    private DisplayMessageUtil displayMessageUtil;

    @InjectMocks
    private UserInteractionUtil userInteractionUtil;

    @Test
    public void shouldReturnNumberOfRounds() {
        final int expectedNumberOfRounds = 5;
        final InputStream in = new ByteArrayInputStream(String.valueOf(expectedNumberOfRounds).getBytes());

        System.setIn(in);

        assertEquals(expectedNumberOfRounds, userInteractionUtil.getNumberOfRounds());

        verify(displayMessageUtil).displayEnterNumberOfRoundsMessage();
        verify(displayMessageUtil, never()).displayInvalidNumberOfRoundsMessage();
    }

    @Test
    public void shouldReturnZeroNumberOfRounds() {
        final InputStream in = new ByteArrayInputStream("someSymbols".getBytes());

        System.setIn(in);

        assertEquals(0, userInteractionUtil.getNumberOfRounds());

        verify(displayMessageUtil, times(2)).displayEnterNumberOfRoundsMessage();
        verify(displayMessageUtil).displayInvalidNumberOfRoundsMessage();
    }

    @Test
    public void shouldReturnUserGesture() throws InvalidGestureKeyException {
        final Gesture expectedGesture = Gesture.PAPER;
        final InputStream in = new ByteArrayInputStream(expectedGesture.getKey().getBytes());

        System.setIn(in);

        when(gestureUtil.getByKey(expectedGesture.getKey())).thenReturn(expectedGesture);

        final Gesture userGesture = userInteractionUtil.getUserGesture();

        assertEquals(expectedGesture, userGesture);

        verify(displayMessageUtil).displayRoundInstructions();
        verify(gestureUtil).getByKey(expectedGesture.getKey());
        verify(displayMessageUtil, never()).displayInvalidGestureMessage();
    }

    @Test
    public void shouldReturnNoUserGesture() throws InvalidGestureKeyException {
        final String nonExistingKey = "nonExistingKey";
        final InputStream in = new ByteArrayInputStream(nonExistingKey.getBytes());

        System.setIn(in);

        when(gestureUtil.getByKey(nonExistingKey)).thenThrow(new InvalidGestureKeyException());

        final Gesture userGesture = userInteractionUtil.getUserGesture();

        assertNull(userGesture);

        verify(displayMessageUtil, times(2)).displayRoundInstructions();
        verify(gestureUtil).getByKey(nonExistingKey);
        verify(displayMessageUtil).displayInvalidGestureMessage();
    }
}