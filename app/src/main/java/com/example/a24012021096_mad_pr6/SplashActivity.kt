package com.example.a24012021096_mad_pr6

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity(), Animation.AnimationListener{
    lateinit var guniframeanim: AnimationDrawable
    lateinit var imglogo: ImageView
    lateinit var gunianim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imglogo = findViewById(R.id.imglogo)
        imglogo.setBackgroundResource(R.drawable.uvpce_animation_list)
        guniframeanim = imglogo.background as AnimationDrawable
        gunianim = AnimationUtils.loadAnimation(this,R.anim.twinkanimation)
        gunianim.setAnimationListener(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus){
            guniframeanim.start()
            imglogo.startAnimation(gunianim)
        } else {
            guniframeanim.stop()
        }
    }
    override fun onAnimationEnd(animation: Animation?) {
        Intent(this, MainActivity::class.java).also{startActivity(it)}
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }

    override fun onAnimationStart(animation: Animation?) {

    }
}