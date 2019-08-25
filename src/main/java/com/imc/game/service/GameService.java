package com.imc.game.service;

import com.google.inject.Inject;
import com.imc.game.entity.Gesture;
import com.imc.game.entity.RoundOutcome;
import com.imc.game.entity.RoundResult;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class GameService {

    private final RoundOutcomeService roundOutcomeService;

    private final UserInputService userInputService;

    private final GestureService gestureService;

    private final UserMessageService userMessageService;

    @Inject
    GameService(final RoundOutcomeService roundOutcomeService, final UserInputService userInputService, final GestureService gestureService, final UserMessageService userMessageService) {
        this.roundOutcomeService = roundOutcomeService;
        this.userInputService = userInputService;
        this.gestureService = gestureService;
        this.userMessageService = userMessageService;
    }

    public List<RoundResult> playGame(final long rounds) {
        userMessageService.displayWelcomeMessage();

        return LongStream.range(1, rounds + 1).mapToObj(roundNumber -> {
            userMessageService.displayRoundNumber(roundNumber);

            final RoundResult roundResult = playRoundAndGetResult(roundNumber);

            userMessageService.displayRoundResult(roundResult.toString());

            return roundResult;
        }).collect(Collectors.toList());
    }

    private RoundResult playRoundAndGetResult(final long roundNumber) {
        final Gesture userGesture = userInputService.getUserGesture();
        final Gesture computerGesture = gestureService.getRandom();
        final RoundOutcome roundOutcome = roundOutcomeService.getRoundOutcome(userGesture, computerGesture);

        return new RoundResult(roundNumber, roundOutcome, userGesture, computerGesture);
    }


}
