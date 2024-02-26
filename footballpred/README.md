This is a project i'm working on to try to predict football games based on the teams xG.

The concept is very easy:
Every team has 4 values
HomexGScored - Expected goals scored per 90 minutes at home
HomexGConceded - Expected goals conceded per 90 minutes at home
AwayxGScored - Expected goals scored per 90 minutes away
AwayxGConceded - Expected goals conceded per 90 minutes away

In a match simulation the home teams xG is calculated by:

(HomexGScored + AwayxGConceded) / 2

Similarly the away teams xG is calculated by:

(AwayxGScored + HomexGConceded) / 2

DATA
The data I've used is from Football Manager 2024 and from FBref