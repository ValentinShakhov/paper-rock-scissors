package com.imc.game;

import com.imc.game.entity.Gesture;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import static com.imc.game.entity.Gesture.*;

public class GestureConfiguration {

    private final EnumMap<Gesture, List<Gesture>> GESTURE_BEATS_GESTURES_MAP = new EnumMap<>(Gesture.class);

    GestureConfiguration() {
        GESTURE_BEATS_GESTURES_MAP.put(PAPER, Collections.singletonList(ROCK));
        GESTURE_BEATS_GESTURES_MAP.put(ROCK, Collections.singletonList(SCISSORS));
        GESTURE_BEATS_GESTURES_MAP.put(SCISSORS, Collections.singletonList(PAPER));
        GESTURE_BEATS_GESTURES_MAP.put(SUPER_GESTURE, Arrays.asList(ROCK, SCISSORS, PAPER));
    }

    public List<Gesture> getWeakerGestures(final Gesture gesture) {
        if (gesture == null) {
            throw new IllegalArgumentException();
        }

        return GESTURE_BEATS_GESTURES_MAP.get(gesture);
    }
}
