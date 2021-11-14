from flask import Flask, jsonify
from flask import request
import sys
#print(sys.path)

from meuModulo import *
mensagem()
import joblib
#importar ia
#ia = import()
app = Flask(__name__)


def ia(sintomas):
    
    #return ia1(sintomas)
    #return ia2(sintomas)
    #return ia3(sintomas)
    #return ia4(sintomas)
    
    ia = joblib.load('my_model_knn.pkl.pkl')
    resultado = ia.predict([sintomas])
    StrA = ''.join(map(str,resultado))
    return StrA
    
    

@app.route("/", methods=['GET', 'POST'])
def postexemplo():
    
    json=request.get_json()
    sintomas = json['sintomas']
    return ia(sintomas)


if __name__ == "__main__":
    app.run()