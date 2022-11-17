package com.robertconstantin.plugins

import com.robertconstantin.di.appModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin


fun Application.configureKoin() {
    install(Koin){
        modules(appModule)
    }
}