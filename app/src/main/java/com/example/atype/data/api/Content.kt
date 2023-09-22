package com.example.atype.data.api

import android.content.Context
import com.example.atype.data.api.Constants.PREFS_NAME
import com.example.atype.data.api.Constants.PREF_KEY
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object Content {

    fun DateFormat(
        timetamp :String?,
        fromFormatformat:String?,
        toFormatformat:String?
    ):String{
        var date:Date? =null
        var res = ""
        try {
            val format = SimpleDateFormat(fromFormatformat)
            date = format.parse(timetamp)
        } catch (e: ParseException){
            e.printStackTrace()
        }
        var dateForm = SimpleDateFormat(toFormatformat)
        res = dateForm.format(date)
        return res
    }
    fun  saveLastSearch(context: Context,query: String){
        val prefs =   context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(PREF_KEY, query).apply()
    }
    fun getLastcSearch (context: Context): String?{
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(PREF_KEY, null)
    }
}