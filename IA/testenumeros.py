# -*- coding: utf-8 -*-
"""TesteNumeros.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1REctDExh2gwu5ZlW8h7ppahtdJAM4wti
"""

# Commented out IPython magic to ensure Python compatibility.
#bibliotecas
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn import datasets
# %matplotlib inline

# Importa o dataset
dfiris=pd.read_csv('testecsv.csv', sep=',', encoding='utf-8')

dfiris.head()

#doencas
dfiris['Doenca'].replace('Infeccao fungica',1, inplace=True)
dfiris['Doenca'].replace('Alergia',2, inplace=True)
dfiris['Doenca'].replace('Refluxo gastroesofagico',3, inplace=True)
dfiris['Doenca'].replace('Colestase cronica',4, inplace=True)
dfiris['Doenca'].replace('Reacao ao medicamento',5, inplace=True)
dfiris['Doenca'].replace('Ulcera peptica',6, inplace=True)
dfiris['Doenca'].replace('AIDS',7, inplace=True)
dfiris['Doenca'].replace('Diabetes',8, inplace=True)
dfiris['Doenca'].replace('Gastroenterite',9, inplace=True)
dfiris['Doenca'].replace('Asma bronquica',10, inplace=True)

#sintomas
dfiris.replace({'vomitos': 'vomito'}, regex=True, inplace=True)
dfiris.replace({'acide': 'acidez'}, regex=True, inplace=True)
dfiris.replace({'coceira': 51}, regex=True, inplace=True) 
dfiris.replace({'erupcao_cutanea': 52}, regex=True, inplace=True)
dfiris.replace({'erupcoes_cutaneas_nodais': 53}, regex=True, inplace=True)
dfiris.replace({'manchas_discromicas': 54}, regex=True, inplace=True)
dfiris.replace({'espirros_continuos': 55}, regex=True, inplace=True) 
dfiris.replace({'tremores': 56}, regex=True, inplace=True)
dfiris.replace({'calafrios': 57}, regex=True, inplace=True)
dfiris.replace({'lacrimejamento': 58}, regex=True, inplace=True)
dfiris.replace({'dor_de_estomago': 59}, regex=True, inplace=True) 
dfiris.replace({'acidez': 60}, regex=True, inplace=True)
dfiris.replace({'ulceras_na_lingua': 61}, regex=True, inplace=True)
dfiris.replace({'vomito': 62}, regex=True, inplace=True)
dfiris.replace({'pele_amarelada': 63}, regex=True, inplace=True)
dfiris.replace({'nausea': 64}, regex=True, inplace=True)
dfiris.replace({'queimacao_na_miccao': 65}, regex=True, inplace=True)
dfiris.replace({'perda_de_apetite': 66}, regex=True, inplace=True)
dfiris.replace({'dor_abdominal': 67}, regex=True, inplace=True)
dfiris.replace({'gases': 68}, regex=True, inplace=True)
dfiris.replace({'perda_de_massa_muscular': 69}, regex=True, inplace=True)
dfiris.replace({'manchas_na_garganta': 70}, regex=True, inplace=True)
dfiris.replace({'febre_alta': 71}, regex=True, inplace=True)
dfiris.replace({'contatos_extraconjugais': 72}, regex=True, inplace=True)
dfiris.replace({'fadiga': 73}, regex=True, inplace=True)
dfiris.replace({'perda_de_peso': 74}, regex=True, inplace=True)
dfiris.replace({'inquietacao': 75}, regex=True, inplace=True)
dfiris.replace({'nivel_de_acucar_irregular': 76}, regex=True, inplace=True)
dfiris.replace({'olhos_fundos': 77}, regex=True, inplace=True)
dfiris.replace({'desidratacao': 78}, regex=True, inplace=True)
dfiris.replace({'diarreia': 79}, regex=True, inplace=True)
dfiris.replace({'tosse': 80}, regex=True, inplace=True)
dfiris.replace({'falta_de_ar': 81}, regex=True, inplace=True)
dfiris.replace({'acidez': 82}, regex=True, inplace=True)

dfiris.describe()

#split treino

dfiris.columns

X=dfiris[['Sintoma_1', 'Sintoma_2', 'Sintoma_3', 'Sintoma_4']]

y=dfiris[['Doenca']]

# Separar dados de treino e dados para teste. (variavel X esta em Maiusculo)
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=101)

X_train[0:1]

y_train[0:1]

#treino
from sklearn.linear_model import LogisticRegression
#modelo matematico usado para predição.
irismodel = LogisticRegression()

irismodel.fit(X_train,y_train)

predito = irismodel.predict(X_test)

y_test[0:10]

predito[0:10]



from sklearn.metrics import classification_report,confusion_matrix

print(confusion_matrix(y_test,predito,y_test.Doenca.unique()))

m=confusion_matrix(y_test,predito,y_test.Doenca.unique())
dfm = pd.DataFrame(m, index = [i for i in y_test.Doenca.unique()
],
                  columns = [i for i in y_test.Doenca.unique()])
plt.figure(figsize = (10,7))

print(classification_report(y_test,predito,y_test.Doenca.unique()))

import joblib
#from sklearn.externals import joblib
joblib.dump(irismodel, 'minhaIA.pkl.pkl')
