package com.imc.game.util;

import com.google.inject.Inject;
import com.imc.game.GestureConfiguration;
import com.imc.game.entity.Gesture;
import com.imc.game.exception.InvalidGestureKeyException;

import java.util.Arrays;
import java.util.Random;

public class GestureUtil {

    private final GestureConfiguration gestureConfiguration;

    @Inject
    public GestureUtil(GestureConfiguration gestureConfiguration) {
        this.gestureConfiguration = gestureConfiguration;
    }

    Gesture getByKey(final String key) throws InvalidGestureKeyException {
        return Arrays.stream(Gesture.values()).filter(gesture -> gesture.getKey().equals(key)).findFirst().orElseThrow(InvalidGestureKeyException::new);
    }

    public Gesture getRandom() {
        return Gesture.values()[new Random().nextInt(Gesture.values().length)];
    }

    boolean beats(final Gesture first, final Gesture second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }

        return gestureConfiguration.getWeakerGestures(first).contains(second);
    }
}