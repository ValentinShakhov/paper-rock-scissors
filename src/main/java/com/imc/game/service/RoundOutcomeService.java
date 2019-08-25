package com.imc.game.service;

import com.imc.game.entity.Gesture;
import com.imc.game.entity.RoundOutcome;

import javax.inject.Inject;

public class RoundOutcomeService {

    private final GestureService gestureService;

    @Inject
    RoundOutcomeService(final GestureService gestureService) {
        this.gestureService = gestureService;
    }

    public RoundOutcome getRoundOutcome(final Gesture userGesture, final Gesture computerGesture) {
        if (userGesture == null || computerGesture == null) {
            throw new IllegalArgumentException();
        }

        if (userGesture == computerGesture) {
            return RoundOutcome.TIE;
        } else if (gestureService.beats(userGesture, computerGesture)) {
            return RoundOutcome.WIN;
        } else return RoundOutcome.LOOSE;
    }
}
