package com.imc.game.util;

import com.imc.game.GesturesConfiguration;
import com.imc.game.entity.Gesture;
import com.imc.game.exception.InvalidGestureKeyException;
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
public class GestureUtilTest {

    @Spy
    private GesturesConfiguration gesturesConfiguration;

    @InjectMocks
    private GestureUtil gestureUtil;

    @Mock
    private Gesture gesture;

    @Test(expected = InvalidGestureKeyException.class)
    public void shouldFailNonExistingKey() throws InvalidGestureKeyException {
        gestureUtil.getByKey("someNonExistingKey");
    }

    @Test
    public void shouldReturnGestureByKey() throws InvalidGestureKeyException {
        assertEquals(gestureUtil.getByKey(Gesture.PAPER.getKey()), Gesture.PAPER);
    }

    @Test
    public void shouldReturnRandomGesture() {
        assertNotNull(gestureUtil.getRandom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailBeatsFirstArgNull() {
        gestureUtil.beats(null, gesture);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailBeatsSecondArgNull() {
        gestureUtil.beats(gesture, null);
    }

    @Test
    public void shouldNotBeatSelf() {
        assertFalse(gestureUtil.beats(gesture, gesture));

        verify(gesturesConfiguration).getWeakerGesturesThan(gesture);
    }
}