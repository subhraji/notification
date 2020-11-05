package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class AlarmActivity extends AppCompatActivity {
    DatePicker pickerDate;
    TimePicker pickerTime;
    TextView tv;
    Button setalarm;
    final static  int RQS_1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        setalarm = (Button)findViewById(R.id.setalarm);
        tv = (TextView)findViewById(R.id.tv);
        pickerTime = (TimePicker) findViewById(R.id.pickertime);
        pickerDate = (DatePicker)findViewById(R.id.pickerdate);

        setalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = "2020-10-5";
                String[] dateParts = date.split("-");
                String day = dateParts[2];
                String month = dateParts[1];
                String year = dateParts[0];

                String time = "17:45:00";
                String[] timeParts = time.split(":");
                String sec = timeParts[2];
                String minute = timeParts[1];
                String hour = timeParts[0];


                Calendar current = Calendar.getInstance();
                Calendar cal = Calendar.getInstance();

                cal.set(
                        Integer.parseInt(year),
                        Integer.parseInt(month),
                        Integer.parseInt(day),
                        Integer.parseInt(hour),
                        Integer.parseInt(minute),
                        Integer.parseInt(sec));

                if(cal.compareTo(current)<= 0){
                    Toast.makeText(AlarmActivity.this,"Invalid",Toast.LENGTH_SHORT).show();
                }else {
                        setAlarm(cal);
                }

            }
        });
    }

    private void setAlarm(Calendar targetCal){
        tv.setText(targetCal.getTime().toString());
        Intent intent = new Intent(getBaseContext(),MyBroadcastReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),pendingIntent);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        if(targetCal.getTime().equals(currentDateTimeString)){
            Toast.makeText(AlarmActivity.this,"Alarm...",Toast.LENGTH_LONG).show();
        }
    }
}