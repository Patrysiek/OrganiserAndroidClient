package com.organiser.task;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.TextView;


public class TaskBar {
    private TextView textView;

    public TaskBar(String description, Context context){
        textView = new TextView(context);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, 0xFF000000);
        textView.setBackground(gd);
        textView.setGravity(Gravity.CENTER);
        textView.setText(description);
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
