package com.example.petevopierre.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.StatusBarManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.petevopierre.Activities.Util.Constants;
import com.example.petevopierre.Activities.bean.Usuario;
import com.example.petevopierre.Activities.bo.UsuarioBo;
import com.example.petevopierre.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import mobi.stos.httplib.HttpAsync;
import mobi.stos.httplib.inter.FutureCallback;

public class LoginActivity extends AppCompatActivity {
    private Button btn_entrar;
    private EditText mPassword, mLogin;
    private Usuario usuario;
    private UsuarioBo usuarioBo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPassword = findViewById(R.id.password);
        mLogin = findViewById(R.id.login);
        btn_entrar = findViewById(R.id.button);

        btnLogin();
    }

    private void btnLogin() {
        btn_entrar.setOnClickListener(v -> {
            try {
                HttpAsync httpAsync = new HttpAsync(new URL(getString(R.string.base_url) + "lojista/login"));
                httpAsync.addParam("email", "pierre@gmail.com");
                httpAsync.addParam("senha", "202cb962ac59075b964b07152d234b70");
                httpAsync.setDebug(true);
                httpAsync.put(new FutureCallback() {
                    @Override
                    public void onBeforeExecute() {

                    }

                    @Override
                    public void onAfterExecute() {

                    }

                    @Override
                    public void onSuccess(int responseCode, Object object) {
                        if (responseCode == 200) {
                            boolean loggedIn;
                            String username;
                            SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE).edit();

                            JSONObject jsonObject = (JSONObject) object;
                            usuario = new Usuario();
                            usuarioBo = new UsuarioBo(LoginActivity.this);
                            try {
                                usuario.setIdlojista(jsonObject.getInt("idLojista"));
                                usuario.setIdloja(jsonObject.getInt("idLoja"));
                                usuario.setNomeLojista(jsonObject.getString("nomeLojista"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            username = usuario.getNomeLojista();
                            loggedIn = true;
                            editor.putBoolean("LOGGEDIN",loggedIn);
                            editor.putString("USERNAME",username);
                            editor.apply();
                            editor.putBoolean("ISLOGGEDIN",loggedIn);
                            usuarioBo.clean();
                            usuarioBo.insert(usuario);
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("USUARIO", usuario);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Exception exception) {

                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        });
    }


}