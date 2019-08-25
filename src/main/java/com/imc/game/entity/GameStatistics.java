package com.imc.game.entity;

import java.util.Map;
import java.util.stream.Collectors;

public class GameStatistics {

    private final Map<RoundOutcome, Long> numberOfEachRoundOutcomes;

    private final GameOutcome gameOutcome;

    public GameStatistics(final Map<RoundOutcome, Long> numberOfEachRoundOutcomes, final GameOutcome gameOutcome) {
        this.numberOfEachRoundOutcomes = numberOfEachRoundOutcomes;
        this.gameOutcome = gameOutcome;
    }

    public String getGameOutcome() {
        return gameOutcome.toString();
    }

    public String getOverallStatistics() {
        return numberOfEachRoundOutcomes.entrySet().stream().map(entry -> String.format("%s: %s", entry.getKey().toString(), entry.getValue())).collect(Collectors.joining(", "));
    }
}
