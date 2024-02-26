package footballpred;

import org.apache.commons.math4.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SoccerMatchSimulator {
    public static void main(String[] args) {
        // Inputs: xG for Team A and Team B
        double xGTeamAHome = 1.5; // Team A's expected goals at home
        double xGTeamBAway = 1.2; // Team B's expected goals away
        double xGTeamAConcededHome = 1.0; // Team A's expected goals conceded at home
        double xGTeamBConcededAway = 1.3; // Team B's expected goals conceded away

        int simulations = 10000; // Number of simulations

        simulateMatches(xGTeamAHome, xGTeamBAway, xGTeamAConcededHome, xGTeamBConcededAway, simulations);
    }

    private static void simulateMatches(double xGTeamA, double xGTeamB, double xGConcededTeamA, double xGConcededTeamB, int simulations) {
        int teamAWins = 0;
        int draws = 0;
        int teamBWins = 0;

        Map<String, Integer> scorelineFrequency = new HashMap<>();

        PoissonDistribution distributionTeamA = new PoissonDistribution(xGTeamA);
        PoissonDistribution distributionTeamB = new PoissonDistribution(xGTeamB);

        for (int i = 0; i < simulations; i++) {
            int goalsTeamA = distributionTeamA.sample();
            int goalsTeamB = distributionTeamB.sample();

            String scoreline = goalsTeamA + " - " + goalsTeamB;
            scorelineFrequency.put(scoreline, scorelineFrequency.getOrDefault(scoreline, 0) + 1);

            if (goalsTeamA > goalsTeamB) {
                teamAWins++;
            } else if (goalsTeamA == goalsTeamB) {
                draws++;
            } else {
                teamBWins++;
            }
        }

        double teamAWinPercentage = (teamAWins / (double) simulations) * 100;
        double drawPercentage = (draws / (double) simulations) * 100;
        double teamBWinPercentage = (teamBWins / (double) simulations) * 100;

        System.out.println("Team A Win Percentage: " + teamAWinPercentage + "%");
        System.out.println("Draw Percentage: " + drawPercentage + "%");
        System.out.println("Team B Win Percentage: " + teamBWinPercentage + "%");

        // Print top 5 most common scorelines
        List<Map.Entry<String, Integer>> sortedScorelines = scorelineFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Top 5 Most Common Scorelines:");
        for (Map.Entry<String, Integer> entry : sortedScorelines) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

