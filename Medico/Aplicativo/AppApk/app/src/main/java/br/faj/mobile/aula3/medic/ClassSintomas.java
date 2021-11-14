package br.faj.mobile.aula3.medic;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.util.List;
import java.util.ArrayList;

public class ClassSintomas {
    public String nome;
    //add
    public int numero;
    List<String> sintomas = new ArrayList<>();

    public String getJson() throws JSONException{
        JSONObject jsonObj = new JSONObject();
        JSONArray integrantesArray = new JSONArray();
        for (String sintoma : sintomas) {
            integrantesArray.put(sintoma);
        }
        jsonObj.put("sintomas", integrantesArray);
        return jsonObj.toString();
    }

    public void fromJson(String json) throws JSONException {
        JSONObject jsonObj = (JSONObject) new JSONTokener(json).nextValue();
        JSONArray senadoresObj = jsonObj.getJSONArray("sintomas");
        sintomas.clear();
        for (int i = 0; i < senadoresObj.length(); i++) {
            sintomas.add(senadoresObj.getString(i));
        }
    }

    public ClassSintomas(String n, int u) {
        this.nome = n;
        //add
        this.numero = u;
    }



    @Override
    public String toString() {
        return this.nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<String> sintomas) {
        this.sintomas = sintomas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
