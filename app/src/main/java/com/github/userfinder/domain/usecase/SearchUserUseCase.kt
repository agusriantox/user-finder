package com.github.userfinder.domain.usecase

import com.github.userfinder.base.UseCase
import com.github.userfinder.data.request.SearchRequest
import com.github.userfinder.data.response.SearchResponse
import com.github.userfinder.domain.repository.GithubRepository

class SearchUserUseCase (private val githubRepository: GithubRepository) : UseCase<SearchResponse, SearchRequest>() {
    override suspend fun run(params: SearchRequest?): SearchResponse {
        return githubRepository.searchUser(params)
    }

}