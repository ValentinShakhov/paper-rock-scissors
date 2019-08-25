package com.imc.game.service;

import com.imc.game.entity.Gesture;
import com.imc.game.exception.InvalidNumberOfRoundsException;

import javax.inject.Inject;
import java.util.Scanner;

public class UserInputConsoleService implements UserInputService {

    private final GestureService gestureService;

    private final UserMessageService userMessageService;

    @Inject
    UserInputConsoleService(final GestureService gestureService, final UserMessageService userMessageService) {
        this.gestureService = gestureService;
        this.userMessageService = userMessageService;
    }

    @Override
    public long getNumberOfRounds() {
        final Scanner scanner = new Scanner(System.in);

        userMessageService.displayEnterNumberOfRoundsMessage();

        long numberOfRounds = 0;
        while (numberOfRounds == 0 && scanner.hasNext()) {
            try {
                final long input = Long.valueOf(scanner.next());

                if (input < 1) {
                    throw new InvalidNumberOfRoundsException();
                }
                numberOfRounds = input;
            } catch (Exception e) {
                userMessageService.displayInvalidNumberOfRoundsMessage();
                userMessageService.displayEnterNumberOfRoundsMessage();
            }
        }

        return numberOfRounds;
    }

    @Override
    public Gesture getUserGesture() {
        final Scanner scanner = new Scanner(System.in);

        userMessageService.displayRoundInstructions();

        Gesture gesture = null;
        while (gesture == null && scanner.hasNext()) {
            try {
                gesture = gestureService.getByKey(scanner.next());
            } catch (Exception e) {
                userMessageService.displayInvalidGestureMessage();
                userMessageService.displayRoundInstructions();
            }
        }

        return gesture;
    }
}
