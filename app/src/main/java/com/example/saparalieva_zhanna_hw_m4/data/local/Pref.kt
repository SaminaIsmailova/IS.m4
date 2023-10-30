package com.example.saparalieva_zhanna_hw_m4.data.local

import android.content.Context

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getName(): String? {
        return pref.getString(NAME_SAVED, null)
    }

    fun saveName(name: String) {
        pref.edit().putString(NAME_SAVED, name).apply()
    }

    fun isShow(): Boolean {
        return pref.getBoolean(SHOWED_KEY, false)
    }


    fun onShowed() {
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

    fun saveImage(image: String) {
        pref.edit().putString(IMAGE_SAVED, image).apply()
    }

    fun getImage(): String? {
        return pref.getString(IMAGE_SAVED, null)
    }

    companion object {
        const val IMAGE_SAVED = "image.saved"
        const val NAME_SAVED = "name.saved"
        const val PREF_NAME = "pref.name"
        const val SHOWED_KEY = "showed.key"
    }


}