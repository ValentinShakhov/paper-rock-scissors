package com.imc.game.util;

import com.imc.game.entity.Gesture;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayMessageUtil {

    private static final String WELCOME_MESSAGE = "Paper - Rock - Scissors";
    private static final String INVALID_NUMBER_OF_ROUNDS_MESSAGE = "!Number of rounds must be a positive number!";
    private static final String ENTER_THE_NUMBER_OF_ROUNDS_MESSAGE = "Please, enter the number of rounds:";
    private static final String INVALID_GESTURE_MESSAGE = "!Invalid gesture!";
    private static final String GAME_DETAILS_MESSAGE = "@@@ Game details @@@";
    private static final String CHOOSE_GESTURE_MESSAGE = "Choose your gesture and press 'Enter'";
    private static final String GAME_FINISHED_MESSAGE = "The game is finished with outcome:";
    private static final String OVERALL_STATISTICS_MESSAGE = "@@@ Overall statistics @@@";
    private static final String ROUND_NUMBER_MESSAGE = "Round #";
    private static final String ANY_MESSAGE_PREFIX = "###";

    public void displayWelcomeMessage() {
        display(WELCOME_MESSAGE);
    }

    void displayRoundInstructions() {
        display(CHOOSE_GESTURE_MESSAGE);
        display(Arrays.stream(Gesture.values()).map(gesture -> String.format("%s: %s", gesture.getKey(), gesture.toString())).collect(Collectors.joining(", ")));
    }

    void displayInvalidNumberOfRoundsMessage() {
        display(INVALID_NUMBER_OF_ROUNDS_MESSAGE);
    }

    void displayEnterNumberOfRoundsMessage() {
        display(ENTER_THE_NUMBER_OF_ROUNDS_MESSAGE);
    }

    void displayInvalidGestureMessage() {
        display(INVALID_GESTURE_MESSAGE);
    }

    public void displayRoundNumber(final long roundNumber) {
        display("===");
        display(String.format("%s %s", ROUND_NUMBER_MESSAGE, roundNumber));
    }

    public void displayRoundResult(final String roundResult) {
        display(roundResult);
    }

    private void display(final String message) {
        System.out.println(String.format("%s %s", ANY_MESSAGE_PREFIX, message));
    }

    public void displayGameResults(final String gameOutcome, final String overallStatistics, final List<String> roundResultList) {
        display(String.format("=== %s %s ===", GAME_FINISHED_MESSAGE, gameOutcome));
        display(GAME_DETAILS_MESSAGE);
        roundResultList.forEach(this::display);
        display(OVERALL_STATISTICS_MESSAGE);
        display(overallStatistics);
    }
}
