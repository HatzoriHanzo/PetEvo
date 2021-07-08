package com.example.petevopierre.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petevopierre.Activities.Util.Constants;
import com.example.petevopierre.Activities.bean.Usuario;
import com.example.petevopierre.R;

public class HomeActivity extends AppCompatActivity {
    private Usuario usuario;
    private TextView mNome;
    private ImageView btnHome;
    private Button btn_pontuarCliente, btn_baixarVoucher;
    private boolean loggedIn;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usuario = (Usuario) getIntent().getSerializableExtra("USUARIO");
        mNome = findViewById(R.id.textViewHelloNome);
        btnHome = findViewById(R.id.btn_sair);
        btn_baixarVoucher = findViewById(R.id.btn_baixar_voucher);
        btn_pontuarCliente = findViewById(R.id.btn_pontuar_cliente);
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE);
        username = preferences.getString("USERNAME","");
        loggedIn = preferences.getBoolean("LOGGEDIN",false);
        if (loggedIn){
            mNome.setText("Olá," + username);
        }
        if (!loggedIn){
            mNome.setText("Olá," + usuario.getNomeLojista());
        }



        btnHome.setOnClickListener(v -> btnHome());

        btn_baixarVoucher.setOnClickListener(v -> btnVoucher());

        btn_pontuarCliente.setOnClickListener(v -> pontuarCliente());

    }

    private void pontuarCliente() {
        startActivity(new Intent(getBaseContext(), QrCodeActivity.class).putExtra("PONTUAR", true));
    }

    public void btnVoucher() {
        startActivity(new Intent(getBaseContext(), QrCodeActivity.class).putExtra("PONTUAR", false));
    }

    public void btnHome() {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE).edit();
        loggedIn = false;
        editor.putBoolean("LOGGEDIN",loggedIn);
        editor.apply();
        startActivity(new Intent(getBaseContext(), LoginActivity.class));

    }


}