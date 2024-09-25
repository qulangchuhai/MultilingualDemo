package com.test.app

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import com.test.app.internationalization.R
import com.test.app.language.LanguageUtil


class TestService : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LanguageUtil.wrap(
                newBase, LanguageUtil.getLocaleByLanguage()
            )
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post {
            Toast.makeText(
                this@TestService,
                Extentions.getString(R.string.app_open_service),
                Toast.LENGTH_SHORT
            ).show()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}