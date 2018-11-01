package com.organiser.acitvities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.organiser.R;
import com.organiser.configuration.ActivityConfig;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityConfig.setFullScreen(this);
        setContentView(R.layout.activity_welcome);
        loadNextActivity();
    }


    private void loadNextActivity() {
        new Handler().postDelayed(() -> startActivity(new Intent(this, LoginActivity.class)),3000);
    }

}
