package com.github.userfinder.ui.activity.main

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.userfinder.R
import com.github.userfinder.base.BaseActivity
import com.github.userfinder.base.State
import com.github.userfinder.databinding.ActivityMainBinding
import com.github.userfinder.ui.adapter.UserAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModel()
    private val userAdapter = UserAdapter()

    override fun getResLayoutId(): Int = R.layout.activity_main

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupView()
        getSearchUserResult()
        getLoadMoreUserResult()
    }

    private fun setupView() {
        binding.vm = viewModel

        binding.btnSearch.setOnClickListener {
            viewModel.onSearchUser()
        }

        binding.rvUser.adapter = userAdapter

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        binding.rvUser.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()

                    if (viewModel.getSearchResult().value?.state != State.LOADING && lastVisiblePosition == userAdapter.getList().size - 1) {
                        viewModel.onLoadMore()
                    }
                }
            }
        })

    }

    private fun getSearchUserResult() {
        viewModel.getSearchResult().observe(this, Observer { result ->
            when (result.state) {
                State.LOADING -> {
                    showProgress()
                    userAdapter.setData(null)
                }
                State.SUCCESS -> {
                    hideProgress()
                    userAdapter.setData(result.data)
                    binding.rvUser.isVisible = true
                    binding.layoutEmptyState.isVisible = false
                }
                State.ERROR -> {
                    hideProgress()
                    binding.rvUser.isVisible = false
                    binding.layoutEmptyState.isVisible = true
                    binding.tvEmptyMessage.text = result.error?.message
                }
            }
        })
    }

    private fun getLoadMoreUserResult() {
        viewModel.getLoadMoreResult().observe(this, Observer { result ->
            when (result.state) {
                State.LOADING -> {
                    showProgress()
                }
                State.SUCCESS -> {
                    hideProgress()
                    userAdapter.addMoreUser(result.data)
                }
                State.ERROR -> {
                    hideProgress()
                }
            }
        })
    }


}