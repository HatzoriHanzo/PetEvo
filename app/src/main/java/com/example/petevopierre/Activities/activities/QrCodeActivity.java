package com.example.petevopierre.Activities.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.petevopierre.Activities.Util.Constants;
import com.example.petevopierre.Activities.Util.Util;
import com.example.petevopierre.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import mobi.stos.httplib.HttpAsync;
import mobi.stos.httplib.inter.FutureCallback;

public class QrCodeActivity extends AppCompatActivity {
    private boolean pontuar;
    private Button btnQrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        btnQrcode = findViewById(R.id.btnQrcode);
        pontuar = (boolean) getIntent().getSerializableExtra("PONTUAR");

        Window window = this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(this.getResources().getColor(R.color.white));


        btnQrcode.setOnClickListener(view -> checkPhonePermission());


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE).edit();
                editor.putInt(Constants.BASE_URL, Integer.parseInt(result.getContents()));
                try {
                    HttpAsync httpAsync = new HttpAsync(new URL(getString(R.string.base_url) + "lojista/getRegraLoja/" + result.getContents()));
                    httpAsync.setDebug(true);
                    httpAsync.get(new FutureCallback() {
                        @Override
                        public void onBeforeExecute() {

                        }

                        @Override
                        public void onAfterExecute() {

                        }

                        @Override
                        public void onSuccess(int responseCode, Object object) {
                            if (responseCode == 200) {
                                int lojaid;
                                float quantitativo;
                                String tipoPontucao;
                                JSONObject jsonObject = (JSONObject) object;
                                if (pontuar) {
                                    try {
                                        lojaid = (jsonObject.getInt("id"));
                                        tipoPontucao = (jsonObject.getString("tipoPontuacaoEnum"));
                                        quantitativo = (jsonObject.getInt("pontuacao"));
//                                    Toast.makeText(QrCodeActivity.this, "tipo de pontuação:"+tipoPontucao, Toast.LENGTH_SHORT).show();
                                        Intent intent = (new Intent(QrCodeActivity.this, PontuarActivity.class));

                                        editor.putInt("ID", lojaid);
                                        editor.putString("TIPOPONTUACAO", tipoPontucao);
                                        editor.putFloat("PONTUACAO",quantitativo);
                                        editor.apply();
                                        startActivity(intent);


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                if (!pontuar) {
                                    try {
                                        lojaid = (jsonObject.getInt("id"));
                                        tipoPontucao = (jsonObject.getString("tipoPontuacaoEnum"));
//                                    Toast.makeText(QrCodeActivity.this, "tipo de pontuação:"+tipoPontucao, Toast.LENGTH_SHORT).show();
                                        Intent intent = (new Intent(QrCodeActivity.this, SucessoActivity.class));
                                        intent.putExtra("TIPOPONTUACAO", tipoPontucao);
                                        intent.putExtra("ID", lojaid);
                                        editor.putInt("ID", lojaid);
                                        editor.putString("TIPOPONTUACAO", tipoPontucao);
                                        editor.apply();
                                        startActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }


                            }

                        }

                        @Override
                        public void onFailure(Exception exception) {

                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

//                finishAffinity();
//                startActivity(new Intent(this, LoginActivity.class));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Util.startQrCodeScan(this);
                }
                break;
            }
        }
    }

    private void checkPhonePermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            Util.startQrCodeScan(this);
        } else {
            System.out.println("Requesting");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

    }
}