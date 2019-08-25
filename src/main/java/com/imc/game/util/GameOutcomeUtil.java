package com.imc.game.util;

import com.imc.game.entity.GameOutcome;
import com.imc.game.entity.RoundOutcome;

import java.util.Map;

class GameOutcomeUtil {

    GameOutcome getGameOutcome(final Map<RoundOutcome, Long> numberOfEachRoundOutcomes) {
        if (numberOfEachRoundOutcomes == null) {
            throw new IllegalArgumentException();
        }

        switch (numberOfEachRoundOutcomes.getOrDefault(RoundOutcome.WIN, 0L).compareTo(numberOfEachRoundOutcomes.getOrDefault(RoundOutcome.LOOSE, 0L))) {
            case -1:
                return GameOutcome.LOOSE;
            case 1:
                return GameOutcome.WIN;
            default:
                return GameOutcome.TIE;
        }
    }
}
