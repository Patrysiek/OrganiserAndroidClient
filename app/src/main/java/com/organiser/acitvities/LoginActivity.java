package com.organiser.acitvities;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.organiser.R;
import com.organiser.configuration.ActivityConfig;
import com.organiser.entities.UserDAO;


public class LoginActivity extends AppCompatActivity {

    String userData;
    EditText login,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityConfig.setFullScreen(this);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login_edit);
        password = findViewById(R.id.password_edit);
    }




    public void login(View view){
        try {
            if(password.getText().length()>0 && login.getText().length()>0) {
                userData = new UserDAO().login(login.getText().toString(),password.getText().toString());
                if(userData!=null) {
                    startMainActivity();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "You pass invalid login or password", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Password or Login is empty", Toast.LENGTH_SHORT);
                toast.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void startMainActivity() {
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        intent.putExtra("userData",userData);
        startActivity(intent);
    }

    public void register(View view){

    }

    public void goOffline(View view){

    }



}
