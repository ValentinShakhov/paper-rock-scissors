package com.imc.game;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.imc.game.entity.GameStatistics;
import com.imc.game.entity.RoundResult;
import com.imc.game.util.DisplayMessageUtil;
import com.imc.game.util.GameStatisticsUtil;
import com.imc.game.util.UserInteractionUtil;

import java.util.List;
import java.util.stream.Collectors;

public class RockPaperScissors {

    private final Game game;

    private final GameStatisticsUtil gameStatisticsUtil;

    private final UserInteractionUtil userInteractionUtil;

    private final DisplayMessageUtil displayMessageUtil;

    @Inject
    RockPaperScissors(final Game game, final GameStatisticsUtil gameStatisticsUtil, final UserInteractionUtil userInteractionUtil, final DisplayMessageUtil displayMessageUtil) {
        this.game = game;
        this.gameStatisticsUtil = gameStatisticsUtil;
        this.userInteractionUtil = userInteractionUtil;
        this.displayMessageUtil = displayMessageUtil;
    }

    public static void main(final String[] args) {
        Guice.createInjector().getInstance(RockPaperScissors.class).start();
    }

    void start() {
        final long numberOfRounds = userInteractionUtil.getNumberOfRounds();
        final List<RoundResult> roundResultList = game.playGame(numberOfRounds);
        final List<String> gameResultsStringList = roundResultList.stream().map(RoundResult::toString).collect(Collectors.toList());
        final GameStatistics gameStatistics = gameStatisticsUtil.getGameStatistics(roundResultList);

        displayMessageUtil.displayGameResults(gameStatistics.getGameOutcome(), gameStatistics.getOverallStatistics(), gameResultsStringList);
    }
}
