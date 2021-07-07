package com.example.petevopierre.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petevopierre.Activities.Util.Constants;
import com.example.petevopierre.R;


public class PontuarActivity extends AppCompatActivity {
    private int id;
    private String tipo;
    private TextView textView_Tipo, textView_Descricao, textView_Resultado;
    private EditText editText_Tipo2;
    private Button btn_pontuar_cliente;
    private ImageView btn_Home;
    private double quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuar);
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE);
        btn_pontuar_cliente = findViewById(R.id.btn_pontuar_cliente);
        btn_Home = findViewById(R.id.btn_sair);
        id = preferences.getInt("ID", 0);
        tipo = preferences.getString("TIPOPONTUACAO", "");
        setViewTexts();

        btn_pontuar_cliente.setOnClickListener(v -> {
            pontuar();
            Toast.makeText(this,
                    "Os pontos estão sendo computados...",
                    Toast.LENGTH_LONG).show();
            Intent intent = (new Intent(this, SucessoActivity.class));
            startActivity(intent);

            btn_Home.setOnClickListener(v1 ->btnHome());

        });

    }

    @Override
    protected void onResume() {


        super.onResume();
    }

    private void pontuar() {
        int count = 0;

        quantidade = Double.parseDouble(String.valueOf(editText_Tipo2.getText()));
        for (int i = 3; i <= quantidade; i += 3) {
            count++;
        }
        textView_Resultado.setText("");
        textView_Resultado.setText("" + count);

    }

    public void btnHome() {
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
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