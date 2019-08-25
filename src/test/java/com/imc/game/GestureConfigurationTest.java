package com.imc.game;

import com.imc.game.entity.Gesture;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class GestureConfigurationTest {

    private final GestureConfiguration gestureConfiguration = new GestureConfiguration();

    @Test
    public void shouldReturnWeakerGesturesForPaper() {
        assertFalse(gestureConfiguration.getWeakerGestures(Gesture.PAPER).isEmpty());
    }

    @Test
    public void shouldReturnWeakerGesturesForRock() {
        assertFalse(gestureConfiguration.getWeakerGestures(Gesture.ROCK).isEmpty());
    }

    @Test
    public void shouldReturnWeakerGesturesForScissors() {
        assertFalse(gestureConfiguration.getWeakerGestures(Gesture.SCISSORS).isEmpty());
    }

    @Test
    public void shouldReturnWeakerGesturesForSuperGesture() {
        assertFalse(gestureConfiguration.getWeakerGestures(Gesture.SUPER_GESTURE).isEmpty());
    }
}