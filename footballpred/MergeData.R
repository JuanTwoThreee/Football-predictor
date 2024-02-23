fm24_data <- read.csv("data/all-players-data.csv")
napoli_players <- subset(fm24_data, Club == "Napoli")

napoli_fbref_data <- read.csv("data/napoli-data-players-fbref.csv")

print(head(napoli_players))
print(head(napoli_fbref_data))

merged_data <- na.omit(merge(napoli_players, napoli_fbref_data, by.x = "Name", by.y = "Unnamed..0_level_0", all.x = TRUE))
print(head(merged_data))

write.csv(merged_data, "data/merged_data.csv", row.names = FALSE)