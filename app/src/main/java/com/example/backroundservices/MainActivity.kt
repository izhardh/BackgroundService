package com.example.backroundservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.backroundservices.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
        //Start Service Variable
        val serviceIntent = Intent(this,BackgroundServices::class.java)

        binding?.btnStart?.setOnClickListener {
            //Call the variable
            startService(serviceIntent)
        }
    }
}