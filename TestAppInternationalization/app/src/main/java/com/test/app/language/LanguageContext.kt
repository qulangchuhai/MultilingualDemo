package com.test.app.language

import android.content.Context
import com.test.app.Extentions
import java.util.Locale

enum class LanguageContext(val languageType: LanguageType) {

    DEFAULT(LanguageType.DEFAULT) {
        private var context: Context =
            LanguageUtil.wrap(Extentions.getApp(), Locale(languageType.language))

        override fun getContext(): Context {
            return context
        }

    },
    ENGLISH(LanguageType.ENGLISH) {
        private var context: Context =
            LanguageUtil.wrap(Extentions.getApp(), Locale(languageType.language))

        override fun getContext(): Context {
            return context
        }

    },
    BANGLADESH(LanguageType.BANGLADESH) {
        private var context: Context =
            LanguageUtil.wrap(Extentions.getApp(), Locale(languageType.language))

        override fun getContext(): Context {
            return context
        }

    };
    abstract fun getContext(): Context

}