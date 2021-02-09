package com.github.userfinder.ui.activity.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.userfinder.base.Data
import com.github.userfinder.base.Error
import com.github.userfinder.base.State
import com.github.userfinder.base.UseCaseResponse
import com.github.userfinder.data.model.User
import com.github.userfinder.data.request.SearchRequest
import com.github.userfinder.data.response.SearchResponse
import com.github.userfinder.domain.usecase.SearchUserUseCase

class MainViewModel constructor(private val searchUserUseCase: SearchUserUseCase) : ViewModel() {

    val searchField = ObservableField<String>()

    private val perPage : Int = 20
    private var page : Int = 1
    private var searchRequest = SearchRequest(name = searchField.get(), page = page, perPage = perPage)

    private val _searchResult = MutableLiveData<Data<List<User>>>()
    fun getSearchResult(): LiveData<Data<List<User>>> = _searchResult

    private val _loadMoreResult = MutableLiveData<Data<List<User>>>()
    fun getLoadMoreResult(): LiveData<Data<List<User>>> = _loadMoreResult


    fun onSearchUser() {
        searchRequest.name = searchField.get()
        searchRequest.page = 1

        _searchResult.value = Data(state = State.LOADING)
        searchUserUseCase.invoke(viewModelScope, searchRequest,
            object : UseCaseResponse<SearchResponse> {
                override fun onSuccess(result: SearchResponse) {
                    if (result.totalCount > 0) {
                        _searchResult.value = Data(state = State.SUCCESS, data = result.items)
                    } else {
                        _searchResult.value = Data(state = State.ERROR, error = Error(message = "Oops, user not found"))
                    }
                }

                override fun onError(e: Throwable?) {
                    _searchResult.value = Data(state = State.ERROR, error = Error(message = "We're sorry there is no response from the server, please try again"))
                }

            })
    }

    fun onLoadMore(){
        page += 1
        searchRequest.page = page

        _loadMoreResult.value = Data(state = State.LOADING)
        searchUserUseCase.invoke(viewModelScope, searchRequest,
            object : UseCaseResponse<SearchResponse> {
                override fun onSuccess(result: SearchResponse) {
                    if (result.totalCount > 0) {
                        _loadMoreResult.value = Data(state = State.SUCCESS, data = result.items)
                    } else {
                        _loadMoreResult.value = Data(state = State.ERROR, error = Error(message = "Oops, user not found"))
                    }
                }

                override fun onError(e: Throwable?) {
                    _loadMoreResult.value = Data(state = State.ERROR, error = Error(message = "We're sorry there is no response from the server, please try again"))
                }

            })
    }

}