package footballpred;

public class Team {
    String teamName;
    double xGH; // Expected goals scored at home
    double xGCH; // Expected goals conceded at home
    double xGA; // Expected goals scored away
    double xGCA; // Expected goals conceded away
 

  

    public Team(String team, double xGH, double xGCH, double xGA, double xGCA
  
        ) {
        this.teamName = team;
        this.xGH = xGH;
        this.xGCH = xGCH;
        this.xGA = xGA;
        this.xGCA = xGCA;
       
    }

    @Override
    public String toString(){
        return teamName + "\nHome\nxG: " + xGH + "  xGA: " + xGCH + "\nAway\nxG: " + xGA + " xGA: " + xGCA +"\n\n";
    }

    public String getTeamName(){
        return teamName;
    }
}
