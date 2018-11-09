package com.organiser.acitvities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.organiser.R;
import com.organiser.helpers.LoginChecker;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView textView = findViewById(R.id.hidden_welcome_button);
        loadNextActivity();
    }

        private void loadNextActivity() {
        if(LoginChecker.getUser(this)==null)
            new Handler().postDelayed(() -> startActivity(new Intent(this, LoginActivity.class)),3000);
        else
            new Handler().postDelayed(() -> startActivity(new Intent(this, MainActivity.class)),3000);
        }


}
