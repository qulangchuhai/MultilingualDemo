package com.test.app

import android.content.Context
import androidx.annotation.StringRes
import com.test.app.language.LanguageUtil

object Extentions {
    fun getString(@StringRes resId: Int) = getContext().getString(resId)

    private fun getContext(): Context {
        return LanguageUtil.getLanguageContext()
    }

    fun getApp() = TestApplication.instance()
}

