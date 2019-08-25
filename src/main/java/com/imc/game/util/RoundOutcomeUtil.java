package com.imc.game.util;

import com.google.inject.Inject;
import com.imc.game.entity.Gesture;
import com.imc.game.entity.RoundOutcome;

public class RoundOutcomeUtil {

    private final GestureUtil gestureUtil;

    @Inject
    public RoundOutcomeUtil(final GestureUtil gestureUtil) {
        this.gestureUtil = gestureUtil;
    }

    public RoundOutcome getRoundOutcome(final Gesture userGesture, final Gesture computerGesture) {
        if (userGesture == null || computerGesture == null) {
            throw new IllegalArgumentException();
        }

        if (userGesture == computerGesture) {
            return RoundOutcome.TIE;
        } else if (gestureUtil.beats(userGesture, computerGesture)) {
            return RoundOutcome.WIN;
        } else return RoundOutcome.LOOSE;
    }
}
