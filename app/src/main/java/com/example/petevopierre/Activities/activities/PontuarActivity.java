package com.example.petevopierre.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petevopierre.Activities.Util.Constants;
import com.example.petevopierre.R;


public class PontuarActivity extends AppCompatActivity {
    private int id;
    private String tipo, username;
    private TextView textView_Tipo, textView_Descricao, textView_Resultado, btn_Home;
    private EditText editText_Tipo2;
    private Button btn_pontuar_cliente;
    private double quantidade;
    private float quantitativo;
    private boolean loggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuar);
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE);
        btn_pontuar_cliente = findViewById(R.id.btn_pontuar_cliente);
        btn_Home = findViewById(R.id.logout_text);
        id = preferences.getInt("ID", 0);
        quantitativo = preferences.getFloat("PONTUACAO", 0);
        tipo = preferences.getString("TIPOPONTUACAO", "");
        username = preferences.getString("USERNAME", "");

        setViewTexts();

        btn_pontuar_cliente.setOnClickListener(v -> {

            pontuar();
            Toast.makeText(this,
                    "Os pontos estão sendo computados...",
                    Toast.LENGTH_LONG).show();
            Intent intent = (new Intent(this, SucessoActivity.class));
            startActivity(intent);


        });
        btn_Home.setOnClickListener(v1 -> btnHome());

    }


    private void pontuar() {
        int count = 0;
        quantidade = Double.parseDouble(String.valueOf(editText_Tipo2.getText()));

        for (float i = quantitativo; i <= quantidade; i += quantitativo) {
            count++;
        }
        textView_Resultado.setText("" + count);
    }

    public void btnHome() {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE).edit();
        editor.putString("USERNAME", username);
        editor.apply();
        startActivity(new Intent(getBaseContext(), HomeActivity.class));
    }


    private void setViewTexts() {
        textView_Tipo = findViewById(R.id.textViewHelloNome);
        textView_Descricao = findViewById(R.id.txtView_Descricao);
        editText_Tipo2 = findViewById(R.id.edtText_adcQuantidade);
        textView_Resultado = findViewById(R.id.editTxtQuantidade);

        if (tipo.equals("QUANTIDADE")) {
            textView_Tipo.setText("Quantidade:");
            textView_Descricao.setHint("Atribua o valor do cupom pela quantidade.");
            editText_Tipo2.setHint("Adicione a quantidade");
        }
        if (tipo.equals("VALOR")) {
            textView_Tipo.setText("Valor:");
            textView_Descricao.setHint("Atribua o valor do cupom.");
            editText_Tipo2.setHint("Adicione o valor");

        }

    }
}