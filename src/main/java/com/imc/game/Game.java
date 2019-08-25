package com.imc.game;

import com.google.inject.Inject;
import com.imc.game.entity.Gesture;
import com.imc.game.entity.RoundOutcome;
import com.imc.game.entity.RoundResult;
import com.imc.game.util.DisplayMessageUtil;
import com.imc.game.util.GestureUtil;
import com.imc.game.util.RoundOutcomeUtil;
import com.imc.game.util.UserInteractionUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class Game {

    private final RoundOutcomeUtil roundOutcomeUtil;

    private final UserInteractionUtil userInteractionUtil;

    private final GestureUtil gestureUtil;

    private final DisplayMessageUtil displayMessageUtil;

    @Inject
    Game(final RoundOutcomeUtil roundOutcomeUtil, final UserInteractionUtil userInteractionUtil, final GestureUtil gestureUtil, final DisplayMessageUtil displayMessageUtil) {
        this.roundOutcomeUtil = roundOutcomeUtil;
        this.userInteractionUtil = userInteractionUtil;
        this.gestureUtil = gestureUtil;
        this.displayMessageUtil = displayMessageUtil;
    }

    List<RoundResult> playGame(final long rounds) {
        displayMessageUtil.displayWelcomeMessage();

        return LongStream.range(1, rounds + 1).mapToObj(roundNumber -> {
            displayMessageUtil.displayRoundNumber(roundNumber);

            final RoundResult roundResult = playRoundAndGetResult(roundNumber);

            displayMessageUtil.displayRoundResult(roundResult.toString());

            return roundResult;
        }).collect(Collectors.toList());
    }

    private RoundResult playRoundAndGetResult(final long roundNumber) {
        final Gesture userGesture = userInteractionUtil.getUserGesture();
        final Gesture computerGesture = gestureUtil.getRandom();
        final RoundOutcome roundOutcome = roundOutcomeUtil.getRoundOutcome(userGesture, computerGesture);

        return new RoundResult(roundNumber, roundOutcome, userGesture, computerGesture);
    }


}
