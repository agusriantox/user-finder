package com.github.userfinder

import com.github.userfinder.di.AppModule
import com.github.userfinder.di.NetworkModule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class KoinModuleTest : AutoCloseKoinTest() {

    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(org.koin.core.logger.Level.DEBUG)
            modules(listOf(AppModule, NetworkModule))
        }.checkModules()
    }

}