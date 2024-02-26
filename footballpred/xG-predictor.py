#Import statements
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn import linear_model

#Read the data 
raw = pd.read_csv("data/merged_data6.csv", sep=",").dropna()
print(raw.head())


names = raw["Name"]
list_of_wanted_cols = ["X1v1","Acc","Aer","Agg","Agi","Ant","Bal","Bra","Cmd",
    "Cnt","Cmp","Cro","Dec","Det","Dri","Fin","Fir","Fla","Han","Hea","Jum","Kic",
    "Ldr","Lon","Mar","OtB","Pac","Pas","Pos","Ref","Sta","Str","Tck","Tea","Tec","Thr","TRO","Vis","Wor", "Cor"]
x = raw[list_of_wanted_cols]
y = raw["xG/90"]
print(x)
print(y)

#Initialize models
PoissonModel = linear_model.PoissonRegressor()
LinearModel = linear_model.LinearRegression()

PoissonModel.fit(x,y)
LinearModel.fit(x,y)
print("models fitted")
# Define Anguissa's values as a 2D array (for a single sample)
anguissa_values = np.array([[1,14,2,14,14,16,18,14,3,14,15,10,14,17,16,11,16,16,1,12,12,1,14,11,12,16,14,16,14,3,15,16,16,15,16,2,3,13,15,4]])
poisson_anguissa_predicted_xG = PoissonModel.predict(anguissa_values)
linear_anguissa_predicted_xG = LinearModel.predict(anguissa_values)

print("Poisson Model xG for Anguissa: ", poisson_anguissa_predicted_xG)
print("Linear Regression Model xG for Anguissa: ", linear_anguissa_predicted_xG)
print("Actual Anguissa xG: 0.11")