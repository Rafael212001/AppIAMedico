import joblib
from flask import request

def mensagem():
    print("Módulo Funcionando")

def receberJson():
    json=request.get_json()
    sintomas = json['sintomas']
    return sintomas


def chamarPredict(sintomas):
    ia = joblib.load('minhaIA.pkl.pkl')
    resultado = ia.predict([sintomas])
    doenca = ''.join(map(str,resultado))
    return doenca
