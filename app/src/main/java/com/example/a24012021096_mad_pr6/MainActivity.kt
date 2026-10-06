package com.example.a24012021096_mad_pr6

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Time
import java.text.SimpleDateFormat
import com.google.android.material.card.MaterialCardView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var alarmanimation: AnimationDrawable
    lateinit var heartanimation: AnimationDrawable
    lateinit var textAlarm: TextView
    lateinit var cardSetAlarm: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val alarm: ImageView = findViewById(R.id.alarmImage)
        alarm.setBackgroundResource(R.drawable.alarm_animation_list)
        alarmanimation = alarm.background as AnimationDrawable

        val heart: ImageView = findViewById(R.id.heartIcon)
        heart.setBackgroundResource(R.drawable.heart_animation_list)
        heartanimation = heart.background as AnimationDrawable

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            alarmanimation.start()
            heartanimation.start()

        } else {
            alarmanimation.stop()
            heartanimation.stop()
        }
    }
}