package com.robertconstantin.features.heroes.data.datasource

import com.robertconstantin.features.heroes.data.datasource.interfaces.ILocalListDataSource
import com.robertconstantin.features.heroes.data.dto.HeroDto


class LocalListDataSourceImpl(
    private val localList: HeroList,
    ): ILocalListDataSource {

    override suspend fun getAllHeroes(): List<HeroDto> {
        return HeroList.listOfHeroes
    }
}