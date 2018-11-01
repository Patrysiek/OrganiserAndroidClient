package com.organiser.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import com.organiser.configuration.ActivityConfig;


public class TaskBar extends AppCompatButton {
    @SuppressLint("ResourceAsColor")
    public TaskBar(String description,Context context){
        super(context);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.BLACK);
        gd.setCornerRadius(20);
        gd.setStroke(5, Color.WHITE);
        this.setBackground(gd);
        this.setWidth(ActivityConfig.getInstance().WIDTH/2);
        this.setTextSize(20);
        this.setGravity(Gravity.CENTER);
        this.setText(description);
        this.setTextColor(Color.WHITE);
        this.setPadding(5,5,5,5);
    }

}
