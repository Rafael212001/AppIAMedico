package br.faj.mobile.aula3.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Sintomas extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas);



        List<ClassSintomas> list = new ArrayList<>();
        list.add(new ClassSintomas("confusao mental"));
        list.add(new ClassSintomas("alteracao na visao"));
        list.add(new ClassSintomas("fraqueza"));
        list.add(new ClassSintomas("alteracao na fala"));
        list.add(new ClassSintomas("Tosse"));
        list.add(new ClassSintomas("Congestao nasal"));
        list.add(new ClassSintomas("Dor de cabeça"));
        list.add(new ClassSintomas("Dor de garganta"));
        list.add(new ClassSintomas("Febre"));
        list.add(new ClassSintomas("Dor no corpo"));
        list.add(new ClassSintomas("Manchas"));
        list.add(new ClassSintomas("Dor no peito"));
        list.add(new ClassSintomas("Falta de ar"));
        list.add(new ClassSintomas("Enjoo"));
        list.add(new ClassSintomas("Diarreia"));
        list.add(new ClassSintomas("Vomito"));
        list.add(new ClassSintomas("Desmaio"));









        ListView listView = findViewById(R.id.sintomasListViewID);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_checked, list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
    }

    public void calcular(View v) {
        ListView listView = findViewById(R.id.sintomasListViewID);
        ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        int total = 0;
        List<ClassSintomas> selecionados = new ArrayList<>();
        for (int i = 0; i < checked.size(); i++) {
            int position = checked.keyAt(i);
            if (checked.valueAt(i)) {
                ClassSintomas s = (ClassSintomas) adapter.getItem(position);
                selecionados.add(s);
            }
        }

        for (ClassSintomas s : selecionados) {
            total ++;
        }


        Toast.makeText(this, "Resultado: "+total, Toast.LENGTH_LONG).show();

        try {
            enviarDados(selecionados);
            Intent intent = new Intent(this, PossivelDoenca.class);
            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Erro no envio:" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    public void enviarDados(List<ClassSintomas> selecionados) throws Exception {
        String json = gerarJSON(selecionados);
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest sr = new StringRequest(Request.Method.POST, getURL(),
                    jsonResp -> tratarRespostaServidor(jsonResp),
                    error -> tratarRespostaErro(error)) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return json.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding. Bytes of %s using %s", json, "utf-8");
                        return null;
                    }
                }
            };
            requestQueue.add(sr);
        } catch (Exception ex) {
            Toast.makeText(this, "Erro ao decodificar dados",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    private void tratarRespostaErro(VolleyError error) {
        Toast.makeText(this, "Decodificar o JSON de resposta" + error.getMessage(),
                Toast.LENGTH_LONG).show();
        error.printStackTrace();
    }

    private void tratarRespostaServidor(String jsonResp) {
        Toast.makeText(this, "Erro no envio do JSON:" + jsonResp,
                Toast.LENGTH_LONG).show();
    }



    private String gerarJSON(List<ClassSintomas> selecionados) throws Exception {
        SharedPreferences sharedPref = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String nome = sharedPref.getString("nome", "Não definido");
        int peso = sharedPref.getInt("peso", 10);
        String cpf = sharedPref.getString("cpf", "000.000.000");

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("nome", nome);  //Obter do SheredPreferences
        jsonObj.put("peso", peso);
        jsonObj.put("cpf", cpf);
        JSONArray sintomasArray = new JSONArray();
        for (ClassSintomas s : selecionados) {
            JSONObject sObj = new JSONObject();
            sObj.put("nome", s.getNome());
            sObj.put("valor", 4);
            sintomasArray.put(sObj);
        }
        jsonObj.put("sintomas", sintomasArray);
        return jsonObj.toString();
    }


    public String getURL() {
        return "http://10.10.10.71:8080/sintomas";
    }
}
