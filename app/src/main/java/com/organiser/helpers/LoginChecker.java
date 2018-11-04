package com.organiser.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.organiser.R;

public class LoginChecker {

    private LoginChecker(){

    }
    public static void saveUser(Context context,String userdata){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.user_data),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.user_data),userdata);
        editor.apply();
    }
    public static String getUser(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.user_data),Context.MODE_PRIVATE);
        return sharedPref.getString(context.getString(R.string.user_data),null);
    }
    public static void clearPrefs(Context context){
        context.getSharedPreferences(context.getString(R.string.user_data),Context.MODE_PRIVATE).edit().clear().apply();
    }
}
