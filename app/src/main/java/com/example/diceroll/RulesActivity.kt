package com.example.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RulesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)
        supportActionBar!!.title = "Rules"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}