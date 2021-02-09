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

    val searchField = ObservableField<String>().apply {
        set("agus")
    }
    private var searchKeyword : String? = null
    private val perPage : Int = 10
    private var page : Int = 1

    private val _searchResult = MutableLiveData<Data<List<User>>>()
    fun getSearchResult(): LiveData<Data<List<User>>> = _searchResult


    fun onSearchUser() {
        _searchResult.value = Data(state = State.LOADING)
        searchKeyword = searchField.get()
        val searchRequest = SearchRequest(name = searchKeyword, page = page, perPage = perPage)
        searchUserUseCase.invoke(viewModelScope, searchRequest,
            object : UseCaseResponse<SearchResponse> {
                override fun onSuccess(result: SearchResponse) {
                    if (result.totalCount > 0) {
                        _searchResult.value = Data(state = State.SUCCESS, data = result.items)
                    } else {
                        _searchResult.value = Data(state = State.ERROR, error = Error(message = "Sorry, user $searchKeyword is not found"))
                    }
                }

                override fun onError(e: Throwable?) {
                    _searchResult.value = Data(state = State.ERROR, error = Error(message = "We're sorry there is no response from the server, please try again"))
                }

            })
    }

}