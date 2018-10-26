package com.organiser.acitvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.organiser.R;
import com.organiser.configuration.ActivityConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView dateText;
    private Button previousDateButton,nextDateButton;
    private SimpleDateFormat format;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityConfig.setFullScreen(this);
        setContentView(R.layout.activity_main);

        format = new SimpleDateFormat("YYYY/MMM/dd", Locale.ENGLISH);
        calendar = Calendar.getInstance();

        initDateText();

        previousDateButton = findViewById(R.id.previous_date_button);
        nextDateButton = findViewById(R.id.next_date_button);
    }

    private void initDateText() {
        dateText = findViewById(R.id.date_text);
        try {
            Date date = new Date(System.currentTimeMillis());
            setDate(date.toString(),0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private void setDateText(String date){
        dateText.setText(date);
    }
    private String getDate(){
        return dateText.getText().toString();
    }

    private void setDate(String curDate, int day) throws ParseException {
        final Date date = new Date(curDate);
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        setDateText( calendar.getTime().toString());
    }

    public void nextDay(View view) throws ParseException {
        setDate(getDate(),1);
    }
    public void previousDay(View view) throws ParseException {
        setDate(getDate(),-1);
    }

}
