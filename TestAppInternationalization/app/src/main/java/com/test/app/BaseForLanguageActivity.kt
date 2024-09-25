package com.test.app

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.test.app.language.LanguageUtil

abstract class BaseForLanguageActivity<VD : ViewDataBinding> : Activity() {
    protected var bindingView: VD? = null

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LanguageUtil.wrap(
                newBase, LanguageUtil.getLocaleByLanguage()
            )
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView = DataBindingUtil.inflate(
            this.layoutInflater,
            getlayoutId(), null as ViewGroup?, false
        )
        window.setContentView(bindingView?.root)

        initView()

    }

    protected abstract fun getlayoutId(): Int
    protected abstract fun initView()


}