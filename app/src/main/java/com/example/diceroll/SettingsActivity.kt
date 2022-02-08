package com.example.diceroll

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class SettingsActivity : AppCompatActivity() {

    enum class Languages () {
        English,
        Chinese,
        Dutch,
        French,
        German,
        Danish,


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        ivEnglish.setOnClickListener{ _ -> updateLanguage(Languages.English)}
        ivDutch.setOnClickListener{ _ -> updateLanguage(Languages.Dutch)}
        ivFrench.setOnClickListener{ _ -> updateLanguage(Languages.French)}
        ivChines.setOnClickListener{ _ -> updateLanguage(Languages.Chinese)}
        ivGerman.setOnClickListener{ _ -> updateLanguage(Languages.German)}
        ivDanish.setOnClickListener{ _ -> updateLanguage(Languages.Danish)}
    }

    private fun updateLanguage(language: SettingsActivity.Languages) {
        val languageToLoad = "fr" // your language

        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
        this.setContentView(R.layout.activity_main)

    }


}