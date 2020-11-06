package com.example.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.util.*

class AlarmKotlinActivity : AppCompatActivity() {
    private lateinit var tvv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_kotlin)

        val btn = findViewById<Button>(R.id.alarm_btn)
        tvv = findViewById<TextView>(R.id.tvv)

        btn.setOnClickListener {
            val date = "2020-10-6"
            val dateParts =
                date.split("-".toRegex()).toTypedArray()
            val day = dateParts[2]
            val month = dateParts[1]
            val year = dateParts[0]
            val time = "15:48:00"
            val timeParts =
                time.split(":".toRegex()).toTypedArray()
            val sec = timeParts[2]
            val minute = timeParts[1]
            val hour = timeParts[0]
            val current = Calendar.getInstance()
            val cal = Calendar.getInstance()
            cal[year.toInt(), month.toInt(), day.toInt(), hour.toInt(), minute.toInt()] = sec.toInt()
            if (cal.compareTo(current) <= 0) {
                Toast.makeText(this@AlarmKotlinActivity, "Invalid", Toast.LENGTH_SHORT).show()
            } else {
                setAlarm(cal)
            }
        }
    }

    private fun setAlarm(targetCal: Calendar) {
        tvv.setText(targetCal.time.toString())
        val intent = Intent(baseContext, KotlinBroadcastReciever::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(baseContext, AlarmActivity.RQS_1, intent, 0)
        val alarmManager =
            getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager[AlarmManager.RTC_WAKEUP, targetCal.timeInMillis] = pendingIntent
        val currentDateTimeString =
            DateFormat.getDateTimeInstance().format(Date())
        if (targetCal.time.equals(currentDateTimeString)) {
            Toast.makeText(this@AlarmKotlinActivity, "Alarm...", Toast.LENGTH_LONG).show()
        }
    }

}