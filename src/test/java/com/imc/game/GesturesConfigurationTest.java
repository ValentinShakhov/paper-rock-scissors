package com.imc.game;

import com.imc.game.entity.Gesture;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class GesturesConfigurationTest {

    private final GesturesConfiguration gesturesConfiguration = new GesturesConfiguration();

    @Test
    public void shouldReturnWeakerGesturesForPaper() {
        assertFalse(gesturesConfiguration.getWeakerGesturesThan(Gesture.PAPER).isEmpty());
    }

    @Test
    public void shouldReturnWeakerGesturesForRock() {
        assertFalse(gesturesConfiguration.getWeakerGesturesThan(Gesture.ROCK).isEmpty());
    }

    @Test
    public void shouldReturnWeakerGesturesForScissors() {
        assertFalse(gesturesConfiguration.getWeakerGesturesThan(Gesture.SCISSORS).isEmpty());
    }

    @Test
    public void shouldReturnWeakerGesturesForSuperGesture() {
        assertFalse(gesturesConfiguration.getWeakerGesturesThan(Gesture.SUPER_GESTURE).isEmpty());
    }
}