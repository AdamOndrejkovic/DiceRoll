package com.example.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        supportActionBar!!.title = "History"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val history = intent.getIntArrayExtra("EXTRA_HISTORY")

        val layout = lyHistoryBox;

        history?.forEachIndexed { index, number ->
            val textView = TextView(this)

            textView.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            textView.text = (index +1).toString() + ": Total thrown " + number
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            layout ?.addView(textView)
        }
    }
}