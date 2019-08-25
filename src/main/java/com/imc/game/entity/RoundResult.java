package com.imc.game.entity;

public class RoundResult {

    private final long roundNumber;
    private final RoundOutcome roundOutcome;
    private final Gesture userGesture;
    private final Gesture computerGesture;

    public RoundResult(final long roundNumber, final RoundOutcome roundOutcome, final Gesture userGesture, final Gesture computerGesture) {
        this.roundNumber = roundNumber;
        this.roundOutcome = roundOutcome;
        this.userGesture = userGesture;
        this.computerGesture = computerGesture;
    }

    public RoundOutcome getRoundOutcome() {
        return roundOutcome;
    }

    @Override
    public String toString() {
        return String.format("Details of Round #%s: User gesture: %s, Computer gesture: %s, Outcome: %s", roundNumber, userGesture, computerGesture, roundOutcome);
    }
}