package com.test.app

import android.app.Application
import kotlin.properties.Delegates

class TestApplication : Application() {

    companion object {
        private var instance: TestApplication by Delegates.notNull()
        fun instance() = instance

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}