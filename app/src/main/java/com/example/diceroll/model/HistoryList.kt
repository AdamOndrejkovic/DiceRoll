package com.example.diceroll.model

class HistoryList {

    private var history = arrayOf<BEHistoryItem>()

    fun getAll(): Array<BEHistoryItem> = history

}