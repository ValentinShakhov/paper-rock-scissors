package com.imc.game.util;

import com.google.inject.Inject;
import com.imc.game.entity.Gesture;
import com.imc.game.exception.InvalidNumberOfRoundsException;

import java.util.Scanner;

public class UserInteractionUtil {

    private final GestureUtil gestureUtil;

    private final DisplayMessageUtil displayMessageUtil;

    @Inject
    public UserInteractionUtil(final GestureUtil gestureUtil, final DisplayMessageUtil displayMessageUtil) {
        this.gestureUtil = gestureUtil;
        this.displayMessageUtil = displayMessageUtil;
    }

    public long getNumberOfRounds() {
        final Scanner scanner = new Scanner(System.in);

        displayMessageUtil.displayEnterNumberOfRoundsMessage();

        long numberOfRounds = 0;
        while (numberOfRounds == 0 && scanner.hasNext()) {
            try {
                final long input = Long.valueOf(scanner.next());

                if (input < 1) {
                    throw new InvalidNumberOfRoundsException();
                }
                numberOfRounds = input;
            } catch (Exception e) {
                displayMessageUtil.displayInvalidNumberOfRoundsMessage();
                displayMessageUtil.displayEnterNumberOfRoundsMessage();
            }
        }

        return numberOfRounds;
    }

    public Gesture getUserGesture() {
        final Scanner scanner = new Scanner(System.in);

        displayMessageUtil.displayRoundInstructions();

        Gesture gesture = null;
        while (gesture == null && scanner.hasNext()) {
            try {
                gesture = gestureUtil.getByKey(scanner.next());
            } catch (Exception e) {
                displayMessageUtil.displayInvalidGestureMessage();
                displayMessageUtil.displayRoundInstructions();
            }
        }

        return gesture;
    }
}
