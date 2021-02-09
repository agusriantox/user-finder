package com.github.userfinder.di

import com.github.userfinder.data.remote.GithubService
import com.github.userfinder.domain.repository.GithubRepository
import com.github.userfinder.domain.repository.GithubRepositoryImpl
import com.github.userfinder.domain.usecase.SearchUserUseCase
import com.github.userfinder.ui.activity.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { MainViewModel(get()) }


    single { createGithubRepository(get()) }
    single { createSearchUseCase(get()) }

}

fun createGithubRepository(githubService: GithubService): GithubRepository {
    return GithubRepositoryImpl(githubService)
}

fun createSearchUseCase(repository: GithubRepository): SearchUserUseCase {
    return SearchUserUseCase(repository)
}
