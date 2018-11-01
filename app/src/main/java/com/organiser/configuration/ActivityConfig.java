package com.organiser.configuration;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

public class ActivityConfig extends Activity{

    public  final int WIDTH =  Resources.getSystem().getDisplayMetrics().widthPixels;
    public  final int HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    private static ActivityConfig activityConfig = new ActivityConfig();
    private ActivityConfig(){
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void setFullScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static ActivityConfig getInstance() {
        return activityConfig;
    }
}
