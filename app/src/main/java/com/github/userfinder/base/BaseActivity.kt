package com.github.userfinder.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.github.userfinder.utils.ProgressDialog

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    private lateinit var progress: ProgressDialog

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

        progress = ProgressDialog(this)

        onActivityCreated(savedInstanceState)
    }

    fun showProgress(){
        progress.show()
    }

    fun hideProgress(){
        progress.dismiss()
    }

}