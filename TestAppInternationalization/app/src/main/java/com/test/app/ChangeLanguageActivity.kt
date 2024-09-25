package com.test.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.test.app.internationalization.R
import com.test.app.internationalization.databinding.ActivitySwitchLanguageLayoutBinding
import com.test.app.language.LanguageType
import com.test.app.language.LanguageUtil

class ChangeLanguageActivity : BaseForLanguageActivity<ActivitySwitchLanguageLayoutBinding>() {

    override fun getlayoutId(): Int {
        return R.layout.activity_switch_language_layout
    }

    override fun initView() {

        bindingView?.changeLanguageZhId?.setOnClickListener {
            switchLanguage(LanguageType.DEFAULT)
        }
        bindingView?.changeLanguageEnId?.setOnClickListener {
            switchLanguage(LanguageType.ENGLISH)

        }
        bindingView?.changeLanguageBnId?.setOnClickListener {
            switchLanguage(LanguageType.BANGLADESH)
        }
    }

    private fun switchLanguage(languageType: LanguageType) {
        LanguageUtil.saveChange(languageType.language)
        startActivity(Intent(this@ChangeLanguageActivity,MainActivity::class.java))
    }
}

