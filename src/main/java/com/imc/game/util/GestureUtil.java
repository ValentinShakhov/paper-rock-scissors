package com.imc.game.util;

import com.google.inject.Inject;
import com.imc.game.GesturesConfiguration;
import com.imc.game.entity.Gesture;
import com.imc.game.exception.InvalidGestureKeyException;

import java.util.Arrays;
import java.util.Random;

public class GestureUtil {

    private final GesturesConfiguration gesturesConfiguration;

    @Inject
    public GestureUtil(GesturesConfiguration gesturesConfiguration) {
        this.gesturesConfiguration = gesturesConfiguration;
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

        return gesturesConfiguration.getWeakerGesturesThan(first).contains(second);
    }
}