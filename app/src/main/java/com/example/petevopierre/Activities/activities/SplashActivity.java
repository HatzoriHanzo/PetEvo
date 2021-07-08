package com.example.petevopierre.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.petevopierre.Activities.Util.Constants;
import com.example.petevopierre.R;

public class SplashActivity extends AppCompatActivity {
    private boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    @Override
    protected void onResume() {
        super.onResume();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES, MODE_PRIVATE);
            loggedIn = preferences.getBoolean("LOGGEDIN",false);
            if (loggedIn){Intent intent = (new Intent(this, HomeActivity.class));
                startActivity(intent);
                this.finish();}
            else{Intent intent = (new Intent(this, LoginActivity.class));
                startActivity(intent);
                this.finish();}

        }, 3000);
    }
}

