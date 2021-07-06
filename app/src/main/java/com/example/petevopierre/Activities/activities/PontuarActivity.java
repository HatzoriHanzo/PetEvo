package com.example.petevopierre.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petevopierre.Activities.Util.Constants;
import com.example.petevopierre.R;

public class PontuarActivity extends AppCompatActivity {
    private int id;
    private String tipo;
    private TextView textView_Tipo, textView_Descricao;
    private EditText editText_Tipo2;
    private Button btn_pontuar_cliente;
    private double quantidade,soma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuar);
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE);
        btn_pontuar_cliente = findViewById(R.id.btn_pontuar_cliente);
        id = preferences.getInt("ID", 0);
        tipo = preferences.getString("TIPOPONTUACAO", "");
        setViewTexts();

        btn_pontuar_cliente.setOnClickListener(v -> {
            pontuar();
        });

    }

    private void pontuar() {
        quantidade = Double.parseDouble(String.valueOf(editText_Tipo2.getText()));
        soma = quantidade / 3;
//        if (soma >)
 

    }


    private void setViewTexts() {
        textView_Tipo = findViewById(R.id.textViewHelloNome);
        textView_Descricao = findViewById(R.id.txtView_Descricao);
        editText_Tipo2 = findViewById(R.id.edtText_adcQuantidade);

        if (tipo == "QUANTIDADE") {
            textView_Tipo.setText("Quantidade:");
            textView_Descricao.setText("Atribua o valor do cupom pela quantidade.");
            editText_Tipo2.setText("Adicione a quantidade");
        }
        if (tipo == "VALOR") {
            textView_Tipo.setText("Valor:");
            textView_Descricao.setText("Atribua o valor do cupom.");
            editText_Tipo2.setText("Adicione o valor");

        }

    }
}