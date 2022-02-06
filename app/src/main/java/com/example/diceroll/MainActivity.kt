package com.example.diceroll

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = "xyz"
    private var history = mutableListOf<Int>()
    private var amount: Int = 1
    private var dices = mutableListOf<ImageView>()

    private val diceId = intArrayOf(
    0,
    R.drawable.dice1,
    R.drawable.dice2,
    R.drawable.dice3,
    R.drawable.dice4,
    R.drawable.dice5,
    R.drawable.dice6
    )

    private val mRandomGenerator = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dices = mutableListOf<ImageView>(ivDice1, ivDice2, ivDice3, ivDice4, ivDice5, ivDice6)
        spAmount.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                amount = (position)


                for (i in 0..5){
                    dices[i].visibility= View.INVISIBLE
                }

                for (i in 0..position){
                    dices[i].visibility= View.VISIBLE
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        btnRoll.setOnClickListener{ _ -> onClickRoll()}
        Log.d(TAG, "OnCreate")
    }

    private fun onClickRoll(){
        var sum: Int = 0
        for (i in 0..amount){
            val image = mRandomGenerator.nextInt(6) + 1
            sum += image
            updateDicesWith(image, i)
        }

        Toast.makeText(this, "Total count: " + (sum) , Toast.LENGTH_SHORT).show()
        if (history.size >= 10){
            history.removeAt(0)
            history.add(sum)
        }else {
            history.add(sum)
        }
    }

    private fun updateDicesWith(image: Int, index: Int) {
        dices[index].setImageResource(diceId[image])
    }

    //used to instantiate menu XML files into Menu objects
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(androidx.core.R.menu.example_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.iHistory -> {
                Intent(this, HistoryActivity::class.java).also {
                    it.putExtra("EXTRA_HISTORY", history.toIntArray())
                    startActivity(it)
                }
            }
            R.id.iRules-> Toast.makeText(this, "Item 2 Selected", Toast.LENGTH_SHORT).show()
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}