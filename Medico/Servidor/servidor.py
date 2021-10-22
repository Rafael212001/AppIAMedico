from flask import Flask, jsonify
from flask import request

app = Flask(__name__)

@app.route("/", methods=['GET', 'POST'])
def postexemplo():
    
    sintomas=request.get_json()
    
    sintoma1 = sintomas['sintomas'][0]
    sintoma2 = sintomas['sintomas'][1]
    sintoma3 = sintomas['sintomas'][2]
    sintoma4 = sintomas['sintomas'][3]
    
    return jsonify(sintoma1)


if __name__ == "__main__":
    app.run()