package com.imc.game.util;

import com.google.inject.Inject;
import com.imc.game.entity.GameStatistics;
import com.imc.game.entity.RoundOutcome;
import com.imc.game.entity.RoundResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameStatisticsUtil {

    private final GameOutcomeUtil gameOutcomeUtil;

    @Inject
    public GameStatisticsUtil(final GameOutcomeUtil gameOutcomeUtil) {
        this.gameOutcomeUtil = gameOutcomeUtil;
    }

    public GameStatistics getGameStatistics(final List<RoundResult> gameResults) {
        if (gameResults == null) {
            throw new IllegalArgumentException();
        }

        final Map<RoundOutcome, Long> numberOfEachRoundOutcomes = gameResults.stream().collect(Collectors.groupingBy(RoundResult::getRoundOutcome, Collectors.counting()));
        return new GameStatistics(numberOfEachRoundOutcomes, gameOutcomeUtil.getGameOutcome(numberOfEachRoundOutcomes));
    }
}