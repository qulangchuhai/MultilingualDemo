package com.test.app.language

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import com.test.app.SpCache.getInstance
import java.util.Locale

object LanguageUtil {

    private const val KEY_LANGUAGE = "sp_language_key"

    fun getLanguageContext(): Context {
        val context = if (getLanguage().equals(LanguageType.ENGLISH.language, ignoreCase = true)) {
            LanguageContext.ENGLISH.getContext()
        } else if (getLanguage().equals(LanguageType.BANGLADESH.language, ignoreCase = true)) {
            LanguageContext.BANGLADESH.getContext()
        } else {
            LanguageContext.DEFAULT.getContext()
        }
        return context
    }


    fun getLocaleByLanguage(): Locale {
        val locale = if (getLanguage().equals(LanguageType.ENGLISH.language, ignoreCase = true)) {
            Locale.ENGLISH
        } else if (getLanguage().equals(LanguageType.BANGLADESH.language, ignoreCase = true)) {
            Locale(LanguageType.BANGLADESH.language)
        } else {
            Locale(LanguageType.DEFAULT.language)
        }
        return locale
    }


    fun wrap(context: Context, newLocale: Locale): Context {
        var context = context
        val res = context.resources
        val configuration = res.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(newLocale)
            val localeList = LocaleList(newLocale)
            LocaleList.setDefault(localeList)
            configuration.setLocales(localeList)
            context = context.createConfigurationContext(configuration)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            configuration.setLocale(newLocale)
            context = context.createConfigurationContext(configuration)
        }
        return ContextWrapper(context)
    }

    private fun getLanguage(): String {
        val defaultLanguage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Resources.getSystem().configuration.locales[0].language
        } else {
            Locale.getDefault().language
        }

        return getInstance()[KEY_LANGUAGE, defaultLanguage]
    }

    private fun setLanguage(language: String?) {
        getInstance().put(KEY_LANGUAGE, language)
    }

    fun saveChange(language: String) {
        setLanguage(language)
    }
}