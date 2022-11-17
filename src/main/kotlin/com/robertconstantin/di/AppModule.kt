package com.robertconstantin.di

import com.google.gson.Gson
import com.robertconstantin.common.Constants.DATABASE_NAME
import com.robertconstantin.features.heroes.data.datasource.CustomHeroDatasourceImpl
import com.robertconstantin.features.heroes.data.datasource.LocalListDataSourceImpl
import com.robertconstantin.features.heroes.data.datasource.HeroList
import com.robertconstantin.features.heroes.data.datasource.interfaces.ICustomHeroDataSource
import com.robertconstantin.features.heroes.data.datasource.interfaces.ILocalListDataSource
import com.robertconstantin.features.heroes.data.repository.HeroRepositoryImpl
import com.robertconstantin.features.heroes.domain.repository.IHeroRepository
import com.robertconstantin.features.heroes.domain.service.HeroServiceImpl
import com.robertconstantin.features.heroes.domain.service.IHeroService
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine

val appModule = module {

    //Provide fake db
    single<HeroList> { HeroList }

    //Provide db
    single {
        val client = org.litote.kmongo.reactivestreams.KMongo.createClient().coroutine
        //provide the CoroutineDb
        client.getDatabase(DATABASE_NAME)
    }

    //Provide Gson
    single { Gson() }

    //Provide list DataSource
    single<ILocalListDataSource> { LocalListDataSourceImpl(get()) }

    //Provide db DataSource
    single<ICustomHeroDataSource> { CustomHeroDatasourceImpl(get()) }

    //Provide Repo
    single<IHeroRepository> { HeroRepositoryImpl(get(), get()) }

    //Provide Service
    single<IHeroService> { HeroServiceImpl(get()) }

}