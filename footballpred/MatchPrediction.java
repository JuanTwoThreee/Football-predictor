package footballpred;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class MatchPrediction {
    double homexG;
    double awayxG;

    double homexGperMin; //kan behöva ändra tiden till actuall matchlängd
    double awayxGperMin;

    int homeTeamWins = 0;
    int awayTeamWins = 0;
    int ties = 0;

    Map<String, Integer> scorelines;

    public MatchPrediction(Team home, Team away) {
        this.homexG = (home.xGH + away.xGCA)/2; 
        this.awayxG = (away.xGA + home.xGCH)/2;
        this.homexGperMin = this.homexG / 90;
        this.awayxGperMin = this.awayxG / 90;

        this.scorelines = new HashMap<>();

    }

    public void simulateOneGame() {
        Random random = new Random();
        int homeGoals = 0;
        int awayGoals = 0;

        for(int i = 0; i <= 90; i++){      
            //avgör hur många mål varje lag gör
            double randomValue = random.nextDouble();
            if(randomValue<=homexGperMin){
                homeGoals++;
            }
            randomValue = random.nextDouble();
            if(randomValue<=awayxGperMin){
                awayGoals++;
            }

        }

        if(homeGoals>awayGoals) { 
            //add win for home team}
            homeTeamWins++;
        }
        else if(homeGoals==awayGoals){
            ties++;
        }
        else if(awayGoals>=homeGoals){
            awayTeamWins++;
        }

        updateScorelineCounts(homeGoals + " - " + awayGoals);
    }



    private void updateScorelineCounts(String newScoreLine) {
        // Here, you'd update the scoreline counts based on the prediction,
        // For simplicity, let's just assume some sample scorelines
        if(scorelines.keySet().contains(newScoreLine)) {
            scorelines.put(newScoreLine, scorelines.get(newScoreLine) + 1);
        }
        else {
            scorelines.put(newScoreLine, 1);
        }
    }

    public Map<String, Integer> getCommonScorelines() {
        return scorelines;
    }


    public void printOutcomeProbabilities(int n) {   
        System.out.println("Home Win Percentage: " + ((double) homeTeamWins / n * 100) + "%");
        System.out.println("Away Win Percentage: " + ((double) awayTeamWins / n * 100) + "%");
        System.out.println("Draw Percentage: " + ((double) ties / n * 100) + "%");

        System.out.println("5 Most Common Scorelines:");
        // Map<String, Integer> scorelineCounts = prediction.getCommonScorelines();
        int count = 0;
        List<Map.Entry<String, Integer>> sortedScorelines = scorelines
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toList());
            
        for (Map.Entry<String, Integer> entry : sortedScorelines) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
            count++;
            if (count >= 15) {
                break;
            }
        }
    }

}
