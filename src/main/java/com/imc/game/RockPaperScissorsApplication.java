package com.imc.game;

import com.google.inject.Guice;
import com.imc.game.entity.GameStatistics;
import com.imc.game.entity.RoundResult;
import com.imc.game.service.GameService;
import com.imc.game.service.GameStatisticsService;
import com.imc.game.service.UserInputService;
import com.imc.game.service.UserMessageService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class RockPaperScissorsApplication {

    private final GameService gameService;

    private final GameStatisticsService gameStatisticsService;

    private final UserInputService userInputService;

    private final UserMessageService userMessageService;

    @Inject
    RockPaperScissorsApplication(final GameService gameService, final GameStatisticsService gameStatisticsService, final UserInputService userInputService, final UserMessageService userMessageService) {
        this.gameService = gameService;
        this.gameStatisticsService = gameStatisticsService;
        this.userInputService = userInputService;
        this.userMessageService = userMessageService;
    }

    public static void main(final String[] args) {
        Guice.createInjector().getInstance(RockPaperScissorsApplication.class).start();
    }

    void start() {
        final long numberOfRounds = userInputService.getNumberOfRounds();
        final List<RoundResult> roundResults = gameService.playGame(numberOfRounds);
        final List<String> gameResultsStrings = roundResults.stream().map(RoundResult::toString).collect(Collectors.toList());
        final GameStatistics gameStatistics = gameStatisticsService.getGameStatistics(roundResults);

        userMessageService.displayGameResults(gameStatistics.getGameOutcome(), gameStatistics.getOverallStatistics(), gameResultsStrings);
    }
}
