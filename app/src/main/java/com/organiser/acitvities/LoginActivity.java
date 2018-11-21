package com.organiser.acitvities;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.organiser.R;
import com.organiser.asyncTasks.LoginTheUser;
import com.organiser.asyncTasks.asyncTasksCallbacks.LoginTheUserCallback;
import com.organiser.helpers.LoginChecker;
import com.organiser.user.UserDAO;


public class LoginActivity extends AppCompatActivity implements LoginTheUserCallback {

    EditText login,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login_edit);
        password = findViewById(R.id.password_edit);
    }

    public void login(View view){
        if(password.getText().length()>0 && login.getText().length()>0) login();
        else showEmptyDataToast();
    }


    private void login() {
        new LoginTheUser(new UserDAO(),this).execute(login.getText().toString(),password.getText().toString());
    }

    public void register(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }

    @Override
    public void startTaskChoiceActivity(String userData) {
        LoginChecker.saveUser(this,userData);
        startActivity(new Intent(this,MenuActivity.class));
    }
    private void showEmptyDataToast() {
        Toast.makeText(getApplicationContext(), "Password or Login is empty", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void invalidLoginOrPassword() {
        Toast.makeText(this, "You pass invalid login or password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void serverError() {
        Toast.makeText(this, "Server error ! ", Toast.LENGTH_SHORT).show();
    }

}
