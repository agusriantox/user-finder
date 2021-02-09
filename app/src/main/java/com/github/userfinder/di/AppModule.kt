package com.github.userfinder.di

import com.github.userfinder.data.remote.GithubService
import com.github.userfinder.domain.repository.GithubRepository
import com.github.userfinder.domain.repository.GithubRepositoryImpl
import com.github.userfinder.domain.usecase.SearchUserUseCase
import org.koin.dsl.module

val AppModule = module {
    single { createGithubRepository(get()) }
    single { createSearchUseCase(get()) }

}

fun createGithubRepository(githubService: GithubService): GithubRepository {
    return GithubRepositoryImpl(githubService)
}

fun createSearchUseCase(repository : GithubRepository): SearchUserUseCase {
    return SearchUserUseCase(repository)
}
