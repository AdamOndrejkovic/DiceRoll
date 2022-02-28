package com.example.diceroll

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_history.*
import com.example.diceroll.model.BEHistoryItem

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        supportActionBar!!.title = "History"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val extras  = intent.getSerializableExtra("EXTRA_HISTORY") as Array<BEHistoryItem>
        val adapter = HistoryAdapter(this, extras)
        val imageAdapter = HistoryImageAdapter(this, extras)
        lvHistoryList.adapter = adapter

        swChangeView.setOnClickListener{ _ ->
            run {
                if (swChangeView.isChecked) {
                    swChangeView.text = "Switch to text"
                    lvHistoryList.adapter = imageAdapter
                } else {
                    swChangeView.text = "Switch to image"
                    lvHistoryList.adapter = adapter
                }
            }
        }

        ivClear.setOnClickListener{ _ ->
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    internal class HistoryAdapter(context: Context,
                                 private val history: Array<BEHistoryItem>
    ) : ArrayAdapter<BEHistoryItem>(context, 0, history)
    {
        override fun getView(position: Int, v: View?, parent: ViewGroup): View {
            var v1: View? = v
            if (v1 == null) {
                val mInflater = LayoutInflater.from(context)
                v1 = mInflater.inflate(R.layout.cell, null)

            }
            val resView: View = v1!!
            val row = history[position]
            val date = resView.findViewById<TextView>(R.id.tvItemDate)
            val item = resView.findViewById<TextView>(R.id.tvItemScore)
            date.text = history[position].date
            item.text = generateScoreString(row)
            return resView
        }


        private fun generateScoreString(beHistoryItem: BEHistoryItem): String {
            var finalString = ""

            if (beHistoryItem.firstDice.toInt() > 0){finalString += "${beHistoryItem.firstDice}"}
            if (beHistoryItem.secondDice.toInt() > 0){finalString += " ${beHistoryItem.secondDice}"}
            if (beHistoryItem.thirdDice.toInt() > 0){finalString += " ${beHistoryItem.thirdDice}"}
            if (beHistoryItem.fourthDice.toInt() > 0){finalString += " ${beHistoryItem.fourthDice}"}
            if (beHistoryItem.fifthDice.toInt() > 0){finalString += " ${beHistoryItem.fifthDice}"}
            if (beHistoryItem.sixthDice.toInt() > 0){finalString += " ${beHistoryItem.sixthDice}"}

            return finalString
        }
    }

    internal class HistoryImageAdapter(context: Context,
                                  private val history: Array<BEHistoryItem>
    ) : ArrayAdapter<BEHistoryItem>(context, 0, history)
    {
        private val diceId = intArrayOf(
            0,
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
        )

        override fun getView(position: Int, v: View?, parent: ViewGroup): View {
            var v1: View? = v
            if (v1 == null) {
                val mInflater = LayoutInflater.from(context)
                v1 = mInflater.inflate(R.layout.image_cell, null)

            }
            val resView: View = v1!!
            val row = history[position]
            val date = resView.findViewById<TextView>(R.id.tvItemDateImage)
            val item = resView.findViewById<TextView>(R.id.lyItemImages) as LinearLayout
            date.text = history[position].date
            val images = generateImageArray(row)
            images.forEach {
                val imageView = ImageView(super.getContext())
                imageView.layoutParams = LinearLayout.LayoutParams(50, 50)
                imageView.setPadding(0,0,5,0)
                imageView.setImageResource(diceId[it])
                imageView.maxWidth = 50
                item.addView(imageView)
            }

            return resView
        }

        private fun generateImageArray(beHistoryItem: BEHistoryItem): MutableList<Int> {
            val images: MutableList<Int> = mutableListOf()

            if (beHistoryItem.firstDice.toInt() > 0){images.add(beHistoryItem.firstDice as Int)}
            if (beHistoryItem.secondDice.toInt() > 0){images.add(beHistoryItem.secondDice as Int)}
            if (beHistoryItem.thirdDice.toInt() > 0){images.add(beHistoryItem.thirdDice as Int)}
            if (beHistoryItem.fourthDice.toInt() > 0){images.add(beHistoryItem.fourthDice as Int)}
            if (beHistoryItem.fifthDice.toInt() > 0){images.add(beHistoryItem.fifthDice as Int)}
            if (beHistoryItem.sixthDice.toInt() > 0){images.add(beHistoryItem.sixthDice as Int)}

            Log.d("xyz", "$images")

            return images;
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        setResult(RESULT_CANCELED, intent)
        finish()
    }
}


