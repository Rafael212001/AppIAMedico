package br.faj.mobile.aula3.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class PossivelDoenca extends AppCompatActivity {

    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possivel_doenca);

        txt = (TextView) findViewById(R.id.Txtdoenca);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String doenca = extras.getString("doenca");
            txt.setText(doenca);
        }
    }
}