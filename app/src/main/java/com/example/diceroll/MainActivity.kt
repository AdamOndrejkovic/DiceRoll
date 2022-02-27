package com.example.diceroll

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroll.model.BEHistoryItem
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = "xyz"
    private var history = mutableListOf<BEHistoryItem>()
    private var amount: Int = 1
    private var dices = mutableListOf<ImageView>()
    private val HISTORY = "HISTORY"
    private val AMOUNT = "AMOUNT"

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

        if (savedInstanceState != null){
            val pairs = savedInstanceState.getSerializable(HISTORY) as Array<BEHistoryItem>
            history.addAll(pairs)
            amount = savedInstanceState.getInt(AMOUNT)
            if (history.isNotEmpty()){
                for (i in 0..5){
                    dices[i].visibility= View.INVISIBLE
                }

                for (i in 0..amount){
                    val image = history.last()
                    Log.d(TAG, "$image information")
                    Log.d(TAG, "$history history")

                    var number = 0
                    when(i){
                        0 ->{number = image.firstDice as Int;}
                        1 ->{number = image.secondDice as Int;}
                        2 ->{number = image.thirdDice as Int;}
                        3 ->{number = image.fourthDice as Int;}
                        4 ->{number = image.fifthDice as Int;}
                        5 ->{number = image.sixthDice as Int;}
                    }
                    dices[i].visibility= View.VISIBLE
                    updateDicesWith(number,i)
                }
            }
        }
        btnRoll.setOnClickListener{ _ -> onClickRoll()}
    }

    private fun onClickRoll(){
        var sum: Int = 0
        val numbers = arrayOf<Int>(0,0,0,0,0,0)
        for (i in 0..amount){
            val image = mRandomGenerator.nextInt(6) + 1
            numbers[i] = image
            sum += image
            updateDicesWith(image, i)
        }

        val record = BEHistoryItem(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5])
        Toast.makeText(this, "Total count: " + (sum) , Toast.LENGTH_SHORT).show()
        if (history.size >= 10){
            history.removeAt(0)
            history.add(record)
        }else {
            history.add(record)
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
                    it.putExtra("EXTRA_HISTORY", history.toTypedArray())
                    startActivity(it)
                }
            }
            R.id.iRules-> Toast.makeText(this, "Item 2 Selected", Toast.LENGTH_SHORT).show()
            R.id.iSettings -> {
                Intent(this, SettingsActivity::class.java).also {
                    startActivity(it)
                }
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(HISTORY, history.toTypedArray())
        outState.putSerializable(AMOUNT, amount)
    }
}