# Install and load the stringdist package
install.packages("stringdist")
library(stringdist)

find_closest_match <- function(name, names_list, threshold = 0.9) {
  # Normalize names for comparison (remove accents and convert to lowercase)
  normalized_name <- stringi::stri_trans_general(name, "Latin-ASCII")
  normalized_names_list <- stringi::stri_trans_general(names_list, "Latin-ASCII")
  
  # Perform approximate matching using amatch
  distances <- stringdist::amatch(normalized_name, normalized_names_list, maxDist = threshold)
  
  # Check if a match is found within the threshold
  closest_index <- which.min(distances)
  if (length(closest_index) > 0 && distances[closest_index] <= threshold) {
    closest_name <- names_list[closest_index]
    return(closest_name)
  } else {
    return(NA)  # No close match found
  }
}


# Load the data
fm24_data <- read.csv("data/all-players-data.csv")
napoli_players <- subset(fm24_data, Club == "Napoli")
napoli_fbref_data <- read.csv("data/napoli-data-players-fbref.csv")

# Create a vector of unique names from both data frames
fm24_names <- unique(napoli_players$Name)
fbref_names <- unique(napoli_fbref_data$Name)

# Find the closest match for each name in fm24_names in fbref_names
matched_names <- sapply(fm24_names, function(name) find_closest_match(name, fbref_names))

# Merge the data frames using the matched names
merged_data <- na.omit(merge(napoli_players, napoli_fbref_data, by.x = "Name", by.y = "Unnamed..0_level_0", all.x = TRUE))

# Save merged data to a CSV file
write.csv(merged_data, "data/merged_data6.csv", row.names = FALSE)
