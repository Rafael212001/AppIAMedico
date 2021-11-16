from flask import Flask, jsonify
from flask import request
from meuModulo import *

#import sys
#print(sys.path)
mensagem()

app = Flask(__name__)

def comunicarIA(sintomas):

    doenca = chamarPredict(sintomas)
    return doenca


@app.route("/", methods=['GET', 'POST'])
def conexaoAndroid():
    
    sintomas = receberJson()
    return comunicarIA(sintomas)


if __name__ == "__main__":
    app.run()