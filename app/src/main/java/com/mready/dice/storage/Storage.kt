package com.mready.dice.storage

import android.content.SharedPreferences
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Storage(private var preferences: SharedPreferences) {
    private var content: String = ""
    private var jsonList = mutableListOf<ResultContent>()

    init {
        content = preferences.getString("shared_preferences_json", "").toString()
        if(content == "")
            this.addNoPreferences()

        jsonList = stringToJsonObject(content)
    }

    // functie care seteaza shared_preferences_json pe cateva valori presabilite
    fun addCustomPreferences(){
        val editor = preferences.edit()
        editor.putString("shared_preferences_json", "[\n" +
                "\t{\n" +
                "\t\t\"total\": 10,\n" +
                "\t\t\"zar1\": 6,\n" +
                "\t\t\"zar2\": 4,\n" +
                "\t\t\"dubla\": false\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"total\": 12,\n" +
                "\t\t\"zar1\": 6,\n" +
                "\t\t\"zar2\": 6,\n" +
                "\t\t\"dubla\": true\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"total\": 3,\n" +
                "\t\t\"zar1\": 1,\n" +
                "\t\t\"zar2\": 2,\n" +
                "\t\t\"dubla\": false\n" +
                "\t}\n" +
                "]")
        editor.commit()
    }

    // functie care seteaza shared_preferences_json pe... nimic XD
    fun addNoPreferences(){
        val editor = preferences.edit()
        editor.putString("shared_preferences_json", "[]")
        editor.commit()

        content = ""
        jsonList = mutableListOf<ResultContent>()
    }

    fun updatePreferences(newPreferences: String){
        val editor = preferences.edit()
        editor.putString("shared_preferences_json", newPreferences)
        editor.commit()
    }

    fun getJsonContent(): String {
        return content
    }

    fun getJsonRaw(): MutableList<ResultContent>{
        return this.jsonList
    }

    fun stringToJsonObject(content: String): MutableList<ResultContent>{
        return Json.decodeFromString<MutableList<ResultContent>>(content)
    }

    fun addElement(total: Int, zar1: Int, zar2: Int, dubla: Boolean){
        var newElement = ResultContent(total, zar1, zar2, dubla)
        jsonList.add(newElement)
        val jsonData = Json.encodeToString(jsonList)
        updatePreferences(jsonData)
    }

    // true if it has elements / false if it doesn't
    fun hasElements(): Boolean{
        return getJsonRaw().size > 0
    }

    fun getLastElement(): ResultContent{
        return getJsonRaw().last()
    }

    fun getElementsSize(): Int {
        return getJsonRaw().size
    }

    // penultimul element
    fun getLastLastElement(): ResultContent {
        return getJsonRaw().elementAt(getElementsSize() - 2)
    }
}