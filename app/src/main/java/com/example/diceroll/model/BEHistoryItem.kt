package com.example.diceroll.model

import java.io.Serializable
import java.sql.Date

data class BEHistoryItem(var firstDice: Number, var secondDice: Number, var thirdDice: Number, var fourthDice: Number, var fifthDice: Number, var sixthDice: Number, var date: String): Serializable