package com.affise.attribution.utils

import android.content.SharedPreferences

private const val SAVE_REPEAT = 3

internal fun SharedPreferences.saveString(key:String, value:String?, count:Int = SAVE_REPEAT) : Boolean {
    val saved = this.edit().apply {
        putString(key, value)
    }.commit()
    if (saved) return true
    if (count <= 0) return false
    return saveString(key, value, count-1)
}

internal fun SharedPreferences.saveBoolean(key:String, value:Boolean, count:Int = SAVE_REPEAT) : Boolean {
    val saved = this.edit().apply {
        putBoolean(key, value)
    }.commit()
    if (saved) return true
    if (count <= 0) return false
    return saveBoolean(key, value, count-1)
}

internal fun SharedPreferences.saveLong(key:String, value:Long, count:Int = SAVE_REPEAT) : Boolean {
    val saved = this.edit().apply {
        putLong(key, value)
    }.commit()
    if (saved) return true
    if (count <= 0) return false
    return saveLong(key, value, count-1)
}

internal fun SharedPreferences.checkSaveString(key:String, func:()->String) {
    if (getString(key, null).isNullOrBlank()) {
        saveString(key, func())
    }
}