package com.github.userfinder.ui.activity.main

import android.os.Bundle
import com.github.userfinder.R
import com.github.userfinder.base.BaseActivity
import com.github.userfinder.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

    override fun getResLayoutId(): Int = R.layout.activity_main

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel.onSearchUser()
    }
}