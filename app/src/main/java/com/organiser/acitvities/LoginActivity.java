package com.organiser.acitvities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.organiser.R;
import com.organiser.asyncTasks.LoginTheUser;
import com.organiser.user.UserDAO;


public class LoginActivity extends AppCompatActivity {

    EditText login,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login_edit);
        password = findViewById(R.id.password_edit);
    }

    public void login(View view){

        if(password.getText().length()>0 && login.getText().length()>0) {
            login();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Password or Login is empty", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void login() {
        new LoginTheUser(new UserDAO(),this).execute(login.getText().toString(),password.getText().toString());
    }

    public void register(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void goOffline(View view){
    }

}
