package com.imc.game;

import com.imc.game.configuration.GesturesMapBasedConfiguration;
import com.imc.game.entity.Gesture;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class GesturesMapBasedConfigurationTest {

    private final GesturesMapBasedConfiguration gesturesMapBasedConfiguration = new GesturesMapBasedConfiguration();

    @Test
    public void shouldReturnWeakerGesturesForPaper() {
        assertFalse(gesturesMapBasedConfiguration.getWeakerGesturesThan(Gesture.PAPER).isEmpty());
    }

    @Test
    public void shouldReturnWeakerGesturesForRock() {
        assertFalse(gesturesMapBasedConfiguration.getWeakerGesturesThan(Gesture.ROCK).isEmpty());
    }

    @Test
    public void shouldReturnWeakerGesturesForScissors() {
        assertFalse(gesturesMapBasedConfiguration.getWeakerGesturesThan(Gesture.SCISSORS).isEmpty());
    }

    @Test
    public void shouldReturnWeakerGesturesForSuperGesture() {
        assertFalse(gesturesMapBasedConfiguration.getWeakerGesturesThan(Gesture.SUPER_GESTURE).isEmpty());
    }
}