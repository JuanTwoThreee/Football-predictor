package footballpred;

public class Team {
    String teamName;
    double xGH; // Expected goals scored at home
    double xGCH; // Expected goals conceded at home
    double xGA; // Expected goals scored away
    double xGCA; // Expected goals conceded away

    int pointsLastFMatches; // Points obtained in the last F matches
    int totalPossiblePoints; // Total possible points in the last F matches

    public Team(String team, double xGH, double xGCH, double xGA, double xGCA, int pointsLastFMatches, int totalPossiblePoints) {
        this.teamName = team;
        this.xGH = xGH;
        this.xGCH = xGCH;
        this.xGA = xGA;
        this.xGCA = xGCA;
        this.pointsLastFMatches = pointsLastFMatches;
        this.totalPossiblePoints = totalPossiblePoints;
    }
}
