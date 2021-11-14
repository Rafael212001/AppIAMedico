import numpy as np
import pandas as pd
import matplotlib as mpl
import matplotlib.pyplot as plt
import seaborn as sns
# %matplotlib inline
from pandas.io import gbq
import sklearn 
from sklearn.preprocessing import  LabelEncoder 
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
from sklearn.neighbors import KNeighborsClassifier

df = pd.read_csv("dataset.csv")
df.head()
df.describe()

df.corr()

X = df[['Symptom_1', 'Symptom_2', 'Symptom_3','Symptom_4','Symptom_5']]

le = LabelEncoder()

for i in X.columns:
    X[i] = le.fit_transform(X[i].astype(str))
print(X.sample(5))

y = df['Disease']
y.head(10)

trainX, testX, trainY, testY = train_test_split(X, y, test_size = 0.2, random_state = 42)

error = []

for i in range(1, 50):
    knn = KNeighborsClassifier(n_neighbors=i)
    knn.fit(trainX, trainY)
    pred_i = knn.predict(testX)
    error.append(np.mean(pred_i != testY))
plt.figure(figsize=(12, 6))
plt.plot(range(1, 50), error, color='red', linestyle='dashed', marker='o',
         markerfacecolor='blue', markersize=10)
plt.title('Error Rate K Value')
plt.xlabel('K Value')
plt.ylabel('Mean Error')

classifier = KNeighborsClassifier(n_neighbors=1)
classifier.fit(trainX, trainY)

y_pred = classifier.predict(testX)
print(testX)
print(type(testX))
#print(classification_report(testY, y_pred))

import joblib
#from sklearn.externals import joblib
joblib.dump(classifier, 'my_model_knn.pkl.pkl')