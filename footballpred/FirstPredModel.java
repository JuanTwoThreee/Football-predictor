package footballpred;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class FirstPredModel {
    //48% chance that City won
    //City won
    //Actual result (1-0) appeared 9.345%
    

    int nbrSimulations = 200000;


    public static void main(String[] args) {
        FirstPredModel model = new FirstPredModel();
        // HashSet<Team> Plteams = model.loadTeams("data/pl.csv");
        // HashSet<Team> SerieAteams = model.loadTeams("data/pl.csv");
        // HashSet<Team> teams = new HashSet<Team>();
        // teams.addAll(Plteams);
        // teams.addAll(SerieAteams);

        HashSet<Team> teams = model.loadTeams("data/pl_serieA_and_laliga_teams.csv");
        System.out.println("Teams List: ");
        teams.stream()
        .forEach(t -> System.out.println(t.getTeamName()));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose home team: ");
        String homeTeamName = scanner.nextLine();
        System.out.print("Choose away team: ");
        String awayTeamName = scanner.nextLine();
       

        Team homeTeam  = null;
        Team awayTeam  = null;
        System.out.println("HomeTeam och awayTeam initialiserade NULL");

        for (Team team : teams) {
            System.out.print(team.teamName);
            if (team.teamName.equals(homeTeamName)) {
                homeTeam = team;
                System.err.println("Found home team: " + team.teamName);
                break;
            }
            else { System.out.println("No Home Team Found ");}
        }
        
        for (Team team : teams) {
            if (team.teamName.equals(awayTeamName)) {
                awayTeam = team;
                System.err.println("Found away team: " + team.teamName);
                break;
            }
            else { System.out.println("No away Team Found");}

        }

        System.out.println(homeTeamName+ awayTeamName);
        System.out.println("For loop färdig");
        System.err.println(homeTeam.toString());
        System.err.println(awayTeam.toString());

        MatchPrediction prediction = new MatchPrediction(homeTeam, awayTeam);

        model.simulateAndPrintOutcome(prediction);

        
    }

    public void simulateAndPrintOutcome(MatchPrediction prediction) {
        for(int i = 0; i < nbrSimulations; i++) {
            prediction.simulateOneGame();
        }
            prediction.printOutcomeProbabilities(nbrSimulations);
    }


    public HashSet<Team> loadTeams(String file){
        
    HashSet<Team> teams  = new HashSet<Team>();
    try {
        File inputFile = new File(file);
        Scanner scan = new Scanner(inputFile);
        System.out.println("scanner funkade");

        String header = scan.nextLine();
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] data = line.split(",");
            System.out.println("hej" + data);
            // Assuming the format is: TeamName,xGH,xGCH,xGA,xGCA,PointsLastFMatches,TotalPossiblePoints
            String teamName = data[0];
            double xGH = Double.parseDouble(data[2]);
            double xGCH = Double.parseDouble(data[1]);
            double xGA = Double.parseDouble(data[3]);
            double xGCA = Double.parseDouble(data[4]);
            // Now you can create Team objects or store the data as required
            // For example:
            Team newTeam = new Team(teamName, xGH, xGCH, xGA, xGCA);
            // Store the team object in your plTeams set or any other data structure
            teams.add(newTeam);
        }
        scan.close();
    } catch (FileNotFoundException e) {
        System.err.println("HITTADE INTE FILEN");
        e.printStackTrace();
    }
    teams.stream()
    .forEach(t -> System.out.println(t.toString()));

    return teams;
}


    
 

}