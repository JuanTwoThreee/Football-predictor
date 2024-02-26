
pl_path <- "data/premier_league/match_logs/"
serie_a_path <- "data/serie_a/match_logs/"

paths <- c(pl_path, serie_a_path)
print(paths)


# Tom data.fram som ska innehålla värden för alla lag
team_stats <- data.frame(
  Team_Name = character(),
  Home_xG_Scored = numeric(),
  Home_xG_Conceded = numeric(),
  Away_xG_Scored = numeric(),
  Away_xG_Conceded = numeric(),
  stringsAsFactors = FALSE
)


get_data_from_teams <- function(path, team_stats) {

  list <- list.files(path, pattern = ".csv", all.files = TRUE)

  for (file in list) {
    team_name <- sub("\\-data.csv", "", file)

    #Alla matcherna för ett lag
    matches <- read.csv(paste(path, file, sep = ""))

    home_matches <- subset(matches, Venue == "Home")
    away_matches <- subset(matches, Venue == "Away")

    team_stats <- rbind(team_stats,
                        data.frame(Team_Name = team_name,
                        Home_xG_Conceded = mean(na.omit(home_matches$xGA)),
                        Home_xG_Scored = mean(na.omit(home_matches$xG)),
                        Away_xG_Scored = mean(na.omit(away_matches$xG)),
                        Away_xG_Conceded = mean(na.omit(away_matches$xGA))))
  }
  return(team_stats)
}

for (path in paths) {
  team_stats <- get_data_from_teams(path, team_stats)
}
print(team_stats)
write.csv(team_stats, "data/pl_and_serieA_teams.csv", row.names = FALSE)