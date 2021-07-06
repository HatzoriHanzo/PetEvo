package com.example.petevopierre.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petevopierre.Activities.bean.Usuario;
import com.example.petevopierre.Activities.bo.UsuarioBo;
import com.example.petevopierre.R;

public class HomeActivity extends AppCompatActivity {
    private Usuario usuario;
    private UsuarioBo usuarioBo;
    private TextView mNome;
    private ImageView btnHome;
    private Button btn_pontuarCliente,btn_baixarVoucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usuario = (Usuario) getIntent().getSerializableExtra("USUARIO");
        mNome = findViewById(R.id.textViewHelloNome);
        btnHome = findViewById(R.id.btn_sair);
        btn_baixarVoucher = findViewById(R.id.btn_pontuar_cliente);
        btn_pontuarCliente = findViewById(R.id.btn_pontuar_cliente);
        mNome.setText("Olá,"+usuario.getNomeLojista());

        btnHome.setOnClickListener(v -> {
            btnHome();
        });

        btn_baixarVoucher.setOnClickListener(v -> {
            btnVoucher();
        });

        btn_pontuarCliente.setOnClickListener(v -> {
            pontuarCliente();
        });

    }

    private void pontuarCliente() {
        startActivity(new Intent(getBaseContext(),QrCodeActivity.class));
    }

    public void btnVoucher(){
        startActivity(new Intent(getBaseContext(),QrCodeActivity.class));
    }

    public void btnHome() {
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
    }


}