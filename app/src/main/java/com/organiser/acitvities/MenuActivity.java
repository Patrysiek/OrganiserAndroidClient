package com.organiser.acitvities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.organiser.R;
import com.organiser.helpers.LoginChecker;
import com.organiser.helpers.ObjectParser;
import com.organiser.user.User;

public class MenuActivity extends AppCompatActivity {
    private User user;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        textView = findViewById(R.id.user_name_menu);
       user = ObjectParser.parseUser(LoginChecker.getUser(this));
       textView.setText(user.getName());
    }


    public void loadMainActivity(View v){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("userNameTable",user.getLogin()+"table");
        startActivity(intent);
    }
    public void loadSharedTasksActivity(View v){
        Intent intent = new Intent(this,SharedTablesActivity.class);
        intent.putExtra("userName",user.getLogin());
        startActivity(intent);
    }
    public void logout(View v){
        LoginChecker.clearPrefs(this);
        Intent i = new Intent(this,LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
