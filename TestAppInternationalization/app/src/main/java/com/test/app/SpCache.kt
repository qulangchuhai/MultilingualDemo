package com.test.app

import android.content.SharedPreferences
import com.test.app.Extentions.getApp


object SpCache {
    private val sp: SharedPreferences =
        getApp().getSharedPreferences("test_sp_cache", 0)


    fun put(key: String?, value: String?) {
        if (value == null) {
            sp.edit().remove(key).commit()
        } else {
            sp.edit().putString(key, value).commit()
        }
    }


    operator fun get(key: String?, defValue: String?): String {
        return sp.getString(key, defValue ?: "") ?: ""
    }


    fun getInstance(): SpCache {
        return this
    }

}