package com.imc.game.service;

import com.imc.game.entity.Gesture;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserMessageService {

    private static final String WELCOME_MESSAGE = "Paper - Rock - Scissors";
    private static final String INVALID_NUMBER_OF_ROUNDS_MESSAGE = "!Number of rounds must be a positive number!";
    private static final String ENTER_THE_NUMBER_OF_ROUNDS_MESSAGE = "Please, enter the number of rounds:";
    private static final String INVALID_GESTURE_MESSAGE = "!Invalid gesture!";
    private static final String GAME_DETAILS_MESSAGE = "@@@ GameService details @@@";
    private static final String CHOOSE_GESTURE_MESSAGE = "Choose your gesture and press 'Enter'";
    private static final String GAME_FINISHED_MESSAGE = "The game is finished with outcome:";
    private static final String OVERALL_STATISTICS_MESSAGE = "@@@ Overall statistics @@@";
    private static final String ROUND_NUMBER_MESSAGE = "Round #";

    private final UserMessageDisplayService userMessageDisplayService;

    @Inject
    UserMessageService(final UserMessageDisplayService userMessageDisplayService) {
        this.userMessageDisplayService = userMessageDisplayService;
    }

    public void displayWelcomeMessage() {
        userMessageDisplayService.display(WELCOME_MESSAGE);
    }

    public void displayRoundInstructions() {
        userMessageDisplayService.display(CHOOSE_GESTURE_MESSAGE);
        userMessageDisplayService.display(Arrays.stream(Gesture.values()).map(gesture -> String.format("%s: %s", gesture.getKey(), gesture.toString())).collect(Collectors.joining(", ")));
    }

    public void displayInvalidNumberOfRoundsMessage() {
        userMessageDisplayService.display(INVALID_NUMBER_OF_ROUNDS_MESSAGE);
    }

    public void displayEnterNumberOfRoundsMessage() {
        userMessageDisplayService.display(ENTER_THE_NUMBER_OF_ROUNDS_MESSAGE);
    }

    public void displayInvalidGestureMessage() {
        userMessageDisplayService.display(INVALID_GESTURE_MESSAGE);
    }

    public void displayRoundNumber(final long roundNumber) {
        userMessageDisplayService.display("===");
        userMessageDisplayService.display(String.format("%s %s", ROUND_NUMBER_MESSAGE, roundNumber));
    }

    public void displayRoundResult(final String roundResult) {
        userMessageDisplayService.display(roundResult);
    }

    public void displayGameResults(final String gameOutcome, final String overallStatistics, final List<String> roundResultList) {
        userMessageDisplayService.display(String.format("=== %s %s ===", GAME_FINISHED_MESSAGE, gameOutcome));
        userMessageDisplayService.display(GAME_DETAILS_MESSAGE);
        roundResultList.forEach(userMessageDisplayService::display);
        userMessageDisplayService.display(OVERALL_STATISTICS_MESSAGE);
        userMessageDisplayService.display(overallStatistics);
    }


}
