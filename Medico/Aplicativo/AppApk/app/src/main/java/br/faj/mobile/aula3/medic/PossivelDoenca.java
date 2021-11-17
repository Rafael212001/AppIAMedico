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
           // String doenca = extras.getString("doenca");
            String doencaId = extras.getString("doenca");
            String doenca = "";

            if(doencaId.equals("1")){
                doenca = "Infecção fungíca";
            }
            if(doencaId.equals("2")){
                doenca = "Alergia";
            }
            if(doencaId.equals("3")){
                doenca = "Refluxo gastroesofagico";
            }
            if(doencaId.equals("4")){
                doenca = "Colestase cronica";
            }
            if(doencaId.equals("5")){
                doenca = "Reacao ao medicamento";
            }
            if(doencaId.equals("6")){
                doenca = "Ulcera peptica";
            }
            if(doencaId.equals("7")){
                doenca = "AIDS";
            }
            if(doencaId.equals("8")){
                doenca = "Diabetes";
            }
            if(doencaId.equals("9")){
                doenca = "Gastroenterite";
            }
            if(doencaId.equals("10")){
                doenca = "Asma bronquica";
            }
            txt.setText(doenca);
        }






    }
}