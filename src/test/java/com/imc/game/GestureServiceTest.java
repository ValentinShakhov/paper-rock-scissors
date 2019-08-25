package com.imc.game;

import com.imc.game.entity.Gesture;
import com.imc.game.exception.InvalidGestureKeyException;
import com.imc.game.service.GestureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GestureServiceTest {

    @Spy
    private GesturesConfiguration gesturesConfiguration;

    @InjectMocks
    private GestureService gestureService;

    @Mock
    private Gesture gesture;

    @Test(expected = InvalidGestureKeyException.class)
    public void shouldFailNonExistingKey() throws InvalidGestureKeyException {
        gestureService.getByKey("someNonExistingKey");
    }

    @Test
    public void shouldReturnGestureByKey() throws InvalidGestureKeyException {
        assertEquals(gestureService.getByKey(Gesture.PAPER.getKey()), Gesture.PAPER);
    }

    @Test
    public void shouldReturnRandomGesture() {
        assertNotNull(gestureService.getRandom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailBeatsFirstArgNull() {
        gestureService.beats(null, gesture);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailBeatsSecondArgNull() {
        gestureService.beats(gesture, null);
    }

    @Test
    public void shouldNotBeatSelf() {
        assertFalse(gestureService.beats(gesture, gesture));

        verify(gesturesConfiguration).getWeakerGesturesThan(gesture);
    }
}