serie_a_path <- "data/serie_a/teams_player_data/"

paths <- c(serie_a_path)
#print(paste("Paths: ", paths))


load_files_to_list <- function(paths) {
  for (path in paths) {
    list <- list.files(path, pattern = ".csv", all.files = TRUE)
  }
  return(list)
}


read_player_from_club <- function(file_list){
  all_clubs_merged_players <- NULL  # Initialize as NULL
  
  for (file in file_list) {
    team_name <- sub("\\-player-data.csv", "", file)  # Use basename to extract file name
    print(team_name)
    fm24_players_from_club <- subset(unfiltered_fm24_players, Club == team_name)
    #print(head(fm24_players_from_club))
    # Read the current file
    fbref_file <- unname(read.csv(paste(serie_a_path, file, sep = "")))
    colnames(fbref_file)[1] <- "Name"
    #print(head(fbref_file))

    # Merge the data
    merged_players <- merge(fm24_players_from_club, fbref_file, by = "Name", all.x = TRUE)
    #print(head(merged_players))
    # Append the merged data to the result
    if (is.null(all_clubs_merged_players)) {
      all_clubs_merged_players <- merged_players
    } else {
      all_clubs_merged_players <- rbind(all_clubs_merged_players, merged_players)
    }
  write.csv(all_clubs_merged_players, "test1.csv", row.names = FALSE)
  }

  
  return(all_clubs_merged_players)
}


#Själva programmet
unfiltered_fm24_players <- read.csv("data/all-players-data.csv");
#print(head(unfiltered_fm24_players))

file_list <- load_files_to_list(paths)
players_from_clubs <- read_player_from_club(file_list)
write.csv(players_from_clubs, "test.csv", row.names = FALSE)

#print(head(player_from_chose_clubs))