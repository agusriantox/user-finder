package com.github.userfinder.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    @LayoutRes
    protected abstract fun getResLayoutId(): Int

    protected abstract fun onActivityCreated(savedInstanceState: Bundle?)

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<VB>(this, getResLayoutId())
            .apply {
                lifecycleOwner = this@BaseActivity
            }

        onActivityCreated(savedInstanceState)
    }

}