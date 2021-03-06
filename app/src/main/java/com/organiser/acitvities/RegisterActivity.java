package com.organiser.acitvities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.organiser.R;
import com.organiser.asyncTasks.UserRegisterer;
import com.organiser.asyncTasks.asyncTasksCallbacks.UserRegistererCallback;

public class RegisterActivity extends AppCompatActivity implements UserRegistererCallback {


    private EditText loginText,nameText,passwordText,confirmPasswordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginText = findViewById(R.id.register_login);
        nameText = findViewById(R.id.register_name);
        passwordText = findViewById(R.id.register_password);
        confirmPasswordText = findViewById(R.id.register_confirm_password);
    }

    public void makeUser(View v){
        String password = passwordText.getText().toString();
        String confirmPassword = confirmPasswordText.getText().toString();

        if(password.length()>7) {
            if (password.equals(confirmPassword)) {
                register();
            }else Toast.makeText(this,"Passwords aren't equal",Toast.LENGTH_SHORT).show();

        } else
            Toast.makeText(this, "Password must be longer than 7 characters", Toast.LENGTH_SHORT).show();

    }

    private void register() {
        new UserRegisterer(this).execute(loginText.getText().toString(), nameText.getText().toString(), passwordText.getText().toString());
    }

    @Override
    public void responseFromUserRegisterer(String answer) {
        if(!answer.equals("1")) {
            answer = "User already exists !";
            Toast.makeText(this, answer, Toast.LENGTH_SHORT).show();
        }else {
            answer = "User created successfully !";
            Toast.makeText(this, answer, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> startActivity(new Intent(this, LoginActivity.class)),3000);
        }
    }
}


