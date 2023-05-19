package com.myanuarbf.hatscoffee

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.myanuarbf.hatscoffee.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler().postDelayed({
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish() }, 2000)

    }
}