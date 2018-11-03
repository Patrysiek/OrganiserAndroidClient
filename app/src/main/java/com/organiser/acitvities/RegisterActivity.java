package com.organiser.acitvities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.organiser.R;
import com.organiser.services.TaskService;
import com.organiser.services.UserService;
import com.organiser.user.UserDAO;

public class RegisterActivity extends AppCompatActivity {


    private EditText loginText,nameText,passwordText,confirmPasswordText;
    private Button registerButton;
    private TaskService taskService;
    private UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginText = findViewById(R.id.register_login);
        nameText = findViewById(R.id.register_name);
        passwordText = findViewById(R.id.register_password);
        confirmPasswordText = findViewById(R.id.register_confirm_password);
        registerButton = findViewById(R.id.make_user_button);


    }

    public void makeUser(View v){
        String password = passwordText.getText().toString();
        String confirmPassword = confirmPasswordText.getText().toString();

        if(password.length()>7) {
            if (password.equals(confirmPassword)) {
                new UserCreator().execute(loginText.getText().toString(), nameText.getText().toString(), password);
            } else
                Toast.makeText(getApplicationContext(), "Password must be longer than 7 characters", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getApplicationContext(),"Passwords aren't equal",Toast.LENGTH_SHORT).show();
    }

    private void loadNextActivity() {
        new Handler().postDelayed(() -> startActivity(new Intent(this, LoginActivity.class)),3000);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private class UserCreator extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            taskService = new TaskService(loginText.getText().toString()+"table");
            userService = new UserService(new UserDAO());
            String answer = null;
            try {
                answer = userService.createUser(strings[0],strings[1],strings[2]);
                taskService.createTaskTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return answer;
        }

        @Override
        protected void onPostExecute(String answer) {
            if(!answer.equals("1")) {
                answer = "User already exists !";
                Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_SHORT).show();
            }else {
                answer = "User created successfully !";
                Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_SHORT).show();
                loadNextActivity();
            }
        }
    }
}


