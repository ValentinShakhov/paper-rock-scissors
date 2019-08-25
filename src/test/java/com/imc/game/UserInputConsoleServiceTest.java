package com.imc.game;

import com.imc.game.entity.Gesture;
import com.imc.game.exception.InvalidGestureKeyException;
import com.imc.game.service.GestureService;
import com.imc.game.service.UserInputConsoleService;
import com.imc.game.service.UserMessageService;
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
public class UserInputConsoleServiceTest {

    @Mock
    private GestureService gestureService;

    @Mock
    private UserMessageService userMessageService;

    @InjectMocks
    private UserInputConsoleService userInputConsoleService;

    @Test
    public void shouldReturnNumberOfRounds() {
        final int expectedNumberOfRounds = 5;
        final InputStream in = new ByteArrayInputStream(String.valueOf(expectedNumberOfRounds).getBytes());

        System.setIn(in);

        assertEquals(expectedNumberOfRounds, userInputConsoleService.getNumberOfRounds());

        verify(userMessageService).displayEnterNumberOfRoundsMessage();
        verify(userMessageService, never()).displayInvalidNumberOfRoundsMessage();
    }

    @Test
    public void shouldReturnZeroNumberOfRounds() {
        final InputStream in = new ByteArrayInputStream("someSymbols".getBytes());

        System.setIn(in);

        assertEquals(0, userInputConsoleService.getNumberOfRounds());

        verify(userMessageService, times(2)).displayEnterNumberOfRoundsMessage();
        verify(userMessageService).displayInvalidNumberOfRoundsMessage();
    }

    @Test
    public void shouldReturnUserGesture() throws InvalidGestureKeyException {
        final Gesture expectedGesture = Gesture.PAPER;
        final InputStream in = new ByteArrayInputStream(expectedGesture.getKey().getBytes());

        System.setIn(in);

        when(gestureService.getByKey(expectedGesture.getKey())).thenReturn(expectedGesture);

        final Gesture userGesture = userInputConsoleService.getUserGesture();

        assertEquals(expectedGesture, userGesture);

        verify(userMessageService).displayRoundInstructions();
        verify(gestureService).getByKey(expectedGesture.getKey());
        verify(userMessageService, never()).displayInvalidGestureMessage();
    }

    @Test
    public void shouldReturnNoUserGesture() throws InvalidGestureKeyException {
        final String nonExistingKey = "nonExistingKey";
        final InputStream in = new ByteArrayInputStream(nonExistingKey.getBytes());

        System.setIn(in);

        when(gestureService.getByKey(nonExistingKey)).thenThrow(new InvalidGestureKeyException());

        final Gesture userGesture = userInputConsoleService.getUserGesture();

        assertNull(userGesture);

        verify(userMessageService, times(2)).displayRoundInstructions();
        verify(gestureService).getByKey(nonExistingKey);
        verify(userMessageService).displayInvalidGestureMessage();
    }
}