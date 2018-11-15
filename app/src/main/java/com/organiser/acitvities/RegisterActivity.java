package com.organiser.acitvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.organiser.R;
import com.organiser.asyncTasks.UserRegisterer;

public class RegisterActivity extends AppCompatActivity {


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
            } else
                Toast.makeText(this, "Password must be longer than 7 characters", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this,"Passwords aren't equal",Toast.LENGTH_SHORT).show();
    }

    private void register() {
        new UserRegisterer(this).execute(loginText.getText().toString(), nameText.getText().toString(), passwordText.getText().toString());
    }

}


