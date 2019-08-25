package com.imc.game;

import com.imc.game.entity.Gesture;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import static com.imc.game.entity.Gesture.*;

public class GesturesConfiguration {

    private final EnumMap<Gesture, List<Gesture>> GESTURE_TO_WEAKER_GESTURES_MAP = new EnumMap<>(Gesture.class);

    GesturesConfiguration() {
        GESTURE_TO_WEAKER_GESTURES_MAP.put(PAPER, Collections.singletonList(ROCK));
        GESTURE_TO_WEAKER_GESTURES_MAP.put(ROCK, Collections.singletonList(SCISSORS));
        GESTURE_TO_WEAKER_GESTURES_MAP.put(SCISSORS, Collections.singletonList(PAPER));
        GESTURE_TO_WEAKER_GESTURES_MAP.put(SUPER_GESTURE, Arrays.asList(ROCK, SCISSORS, PAPER));
    }

    public List<Gesture> getWeakerGesturesThan(final Gesture gesture) {
        if (gesture == null) {
            throw new IllegalArgumentException();
        }

        return GESTURE_TO_WEAKER_GESTURES_MAP.get(gesture);
    }
}
