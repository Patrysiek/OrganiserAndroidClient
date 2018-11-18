package com.organiser.acitvities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.organiser.R;
import com.organiser.helpers.LoginChecker;
import com.organiser.helpers.ObjectParser;
import com.organiser.user.User;

public class MenuActivity extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
       user = ObjectParser.parseUser(LoginChecker.getUser(this));
    }


    public void loadMainActivity(View v){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("userName",user.getName());
        startActivity(intent);
    }
    public void loadSharedTasksActivity(View v){
        Intent intent = new Intent(this,SharedTablesActivity.class);
        intent.putExtra("userName",user.getName());
        startActivity(intent);
    }
    public void logout(View v){
        LoginChecker.clearPrefs(this);
        startActivity(new Intent(this,LoginActivity.class));
    }
}
