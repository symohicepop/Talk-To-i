package com.example.talktoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val SPLASH_SCREEN = 5000

    private lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation

    private lateinit var ImageView:ImageView
    private lateinit var title_txt: TextView
    private lateinit var description_txt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hide status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.hide()

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)


        ImageView = findViewById(R.id.hr_image)
        title_txt = findViewById(R.id.title_text)
        description_txt = findViewById(R.id.description_text)


        ImageView.animation = topAnimation
        title_txt.animation = bottomAnimation
        description_txt.animation = bottomAnimation

        Handler().postDelayed({
            val intent=Intent(this,LogInActivity::class.java)
    startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())
    }
    }








