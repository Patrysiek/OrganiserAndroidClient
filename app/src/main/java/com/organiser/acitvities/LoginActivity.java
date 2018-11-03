package com.organiser.acitvities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.organiser.R;
import com.organiser.services.UserService;
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
            new LoginTheUser(new UserDAO()).execute();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Password or Login is empty", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void startMainActivity(String userData) {
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        intent.putExtra("userData",userData);
        startActivity(intent);
    }

    public void register(View view){
       startActivity(new Intent(this,RegisterActivity.class));
    }

    public void goOffline(View view){
        //load data from local database
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    private class LoginTheUser extends AsyncTask<Void,Void,String>{

        UserService userService;
        LoginTheUser(UserDAO userDAO) {
            this.userService = new UserService(userDAO);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                return userService.login(login.getText().toString(),password.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            if(!s.equals(null) && !s.equals("") && s.length()>0) {
                startMainActivity(s);
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "You pass invalid login or password", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
}
