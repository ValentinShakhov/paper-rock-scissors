package com.imc.game.service;

import com.imc.game.entity.GameStatistics;
import com.imc.game.entity.RoundOutcome;
import com.imc.game.entity.RoundResult;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameStatisticsService {

    private final GameOutcomeService gameOutcomeService;

    @Inject
    GameStatisticsService(final GameOutcomeService gameOutcomeService) {
        this.gameOutcomeService = gameOutcomeService;
    }

    public GameStatistics getGameStatistics(final List<RoundResult> gameResults) {
        if (gameResults == null) {
            throw new IllegalArgumentException();
        }

        final Map<RoundOutcome, Long> numberOfEachRoundOutcomes = gameResults.stream().collect(Collectors.groupingBy(RoundResult::getRoundOutcome, Collectors.counting()));
        return new GameStatistics(numberOfEachRoundOutcomes, gameOutcomeService.getGameOutcome(numberOfEachRoundOutcomes));
    }
}