from bs4 import BeautifulSoup
import pandas as pd


url = "https://fbref.com/en/squads/822bd0ba/Liverpool-Stats"
html_contents = pd.read_html(url)
html_content = html_contents[0]
print(html_content)
# Parse the HTML content
soup = BeautifulSoup(html_content, 'html.parser')

# Find the table body
table_body = soup.find('tbody')

# Initialize a list to store player links
player_links = []

# Find all table rows
rows = table_body.find_all('tr')

# Loop through each row
for row in rows:
    # Find the player name cell
    player_cell = row.find('th', {'data-stat': 'player'})
    # Check if player cell exists
    if player_cell:
        # Find the player link
        player_link = player_cell.find('a')
        # Check if player link exists
        if player_link:
            # Get the href attribute (player link)
            player_href = player_link.get('href')
            # Append the player link to the list
            player_links.append(player_href)

# Print the player links
print(player_links)
