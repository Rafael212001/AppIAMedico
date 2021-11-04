from flask import Flask, jsonify
from flask import request
import sys
print(sys.path)

from meuModulo import *
mensagem()

app = Flask(__name__)


def ia(sintomas):
    
    #return ia1(sintomas)
    #return ia2(sintomas)
    #return ia3(sintomas)
    return ia4(sintomas)
    
    

@app.route("/", methods=['GET', 'POST'])
def postexemplo():
    
    sintomas=request.get_json()
    
    return ia(sintomas)


if __name__ == "__main__":
    app.run()