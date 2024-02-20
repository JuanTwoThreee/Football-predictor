package footballpred;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.Random;

public class FirstPredModel {

    Team homeTeam = new Team("Manchester City", 2.00667, 0.8, 2.38125, 0.925, 13, 15); // xGH, xGCH, xGA, xGCA, points in last F matches, total possible points
    Team awayTeam = new Team("Brentford", 1.723076923, 1.592307692, 1.590909, 1.3272727, 6, 15); // xGH, xGCH, xGA, xGCA, points in last F matches, total possible points
    int nbrSimulations = 100000;

    MatchPrediction prediction = new MatchPrediction(homeTeam, awayTeam);

    public static void main(String[] args) {
        FirstPredModel model = new FirstPredModel();
        model.simulateAndPrintOutcome();
    }

    public void simulateAndPrintOutcome() {
        for(int i = 0; i < nbrSimulations; i++) {
            prediction.simulateOneGame();
        }
            prediction.printOutcomeProbabilities(nbrSimulations);
    }

    


}