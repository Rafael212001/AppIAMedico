package br.faj.mobile.aula3.medic.JSON;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestData extends AppCompatActivity {

    public void resquestData(View v) {
        RequestQueue rq = Volley.newRequestQueue(this);
        String url ="http://localhost:8081/chat";
        StringRequest sr = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("resposta", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(null, "Erro ao enviar: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        rq.add(sr);

    }
}
