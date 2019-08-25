package com.imc.game.service;

import com.imc.game.configuration.GesturesConfiguration;
import com.imc.game.entity.Gesture;
import com.imc.game.exception.InvalidGestureKeyException;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Random;

public class GestureService {

    private final GesturesConfiguration gesturesConfiguration;

    @Inject
    GestureService(final GesturesConfiguration gesturesConfiguration) {
        this.gesturesConfiguration = gesturesConfiguration;
    }

    public Gesture getByKey(final String key) throws InvalidGestureKeyException {
        return Arrays.stream(Gesture.values()).filter(gesture -> gesture.getKey().equals(key)).findFirst().orElseThrow(InvalidGestureKeyException::new);
    }

    public Gesture getRandom() {
        return Gesture.values()[new Random().nextInt(Gesture.values().length)];
    }

    public boolean beats(final Gesture first, final Gesture second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }

        return gesturesConfiguration.getWeakerGesturesThan(first).contains(second);
    }
}