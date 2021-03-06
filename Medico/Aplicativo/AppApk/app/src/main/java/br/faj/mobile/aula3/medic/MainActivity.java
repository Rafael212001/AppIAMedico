package br.faj.mobile.aula3.medic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;



public class MainActivity extends AppCompatActivity {

    public EditText enome;
    public EditText epeso;
    public EditText ecpf;
    boolean dados;
    private TextWatcher cpfMask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        enome = (EditText) findViewById(R.id.Camponome);
        epeso = (EditText) findViewById(R.id.Campopeso);
        ecpf = (EditText) findViewById(R.id.CampoCPF);

        cpfMask = Mask.insert("###.###.###-##", ecpf);
        ecpf.addTextChangedListener(cpfMask);


    }

    public void validarCampos(){

        boolean res = false;

        String nome = enome.getText().toString();
        String peso = epeso.getText().toString();
        String cpf = ecpf.getText().toString();

        if (campoVazio(nome)){
            res = true;
            enome.requestFocus();
        }
        else if (campoVazio(peso)){
            res = true;
            epeso.requestFocus();
        }
        else if (campoVazio(cpf)){
            res = true;
            ecpf.requestFocus();
        }

        if(res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Existem campos vazios! TODOS OS CAMPOS DEVEM SER PREENCHIDOS!");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    public boolean campoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    public void proximaTela(View view){
        String nome = enome.getText().toString();
        String peso = epeso.getText().toString();
        String cpf = ecpf.getText().toString();

        try {
            SharedPreferences sharedPref = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("nome", nome);
            editor.putInt("peso", Integer.parseInt(peso));
            editor.putString("cpf", cpf);
            editor.commit();
        }catch (Exception e){
            Log.i("eee","Erro persistencia");
        }


        if(enome.getText().toString().trim().equals("") ||
                epeso.getText().toString().trim().equals("") ||
                ecpf.getText().toString().trim().equals("") ){
            validarCampos();
        }else {
            Intent intent = new Intent(this, Sintomas.class);
            startActivity(intent);
        }
    }






}
