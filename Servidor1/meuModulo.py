def mensagem():
    print("Funcionando")


def ia1(sintomas):

    return print("Funcionando")

def ia2(sintomas):

    sintoma2 = sintomas['sintomas'][1]

    return (sintoma2)

def ia3(sintomas):

    sintoma3 = sintomas['sintomas'][2]

    return (sintoma3)

def ia4(sintomas):

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

    #le = LabelEncoder()

    #for i in X.columns:
   #    X[i] = le.fit_transform(X[i].astype(str))

    y = df['Disease']
    y.head(10)

    trainX, testX, trainY, testY = train_test_split(X, y, test_size = 0.2, random_state = 42)

    error = []

    classifier = KNeighborsClassifier(n_neighbors=1)
    classifier.fit(trainX, trainY)

    y_pred = classifier.predict(testX)

    # Depois de treinado Exportar
    classifier.export('.')
    
    return print("oi")

def mensagem():
    print("Funcionando")