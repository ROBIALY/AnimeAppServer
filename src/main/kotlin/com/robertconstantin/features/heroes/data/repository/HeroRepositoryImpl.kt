package com.robertconstantin.features.heroes.data.repository

import com.robertconstantin.features.heroes.data.datasource.interfaces.ICustomHeroDataSource
import com.robertconstantin.features.heroes.data.datasource.interfaces.ILocalListDataSource
import com.robertconstantin.features.heroes.data.mapper.toCustomHeroDM
import com.robertconstantin.features.heroes.data.mapper.toCustomHeroDto
import com.robertconstantin.features.heroes.data.mapper.toHeroDM
import com.robertconstantin.features.heroes.domain.model.CustomHeroDM
import com.robertconstantin.features.heroes.domain.model.HeroDM
import com.robertconstantin.features.heroes.domain.repository.IHeroRepository

class HeroRepositoryImpl(
    private val localList: ILocalListDataSource,
    private val customHeroDataSource: ICustomHeroDataSource
): IHeroRepository {

    override suspend fun getAllHeroes(): List<HeroDM> {
        return localList.getAllHeroes().map { heroDto -> heroDto.toHeroDM() }
    }

    override suspend fun getAllCustomHeroes(): List<CustomHeroDM> {
        return customHeroDataSource.getAllCustomHeroes().map { it.toCustomHeroDM() }
    }

    override suspend fun createHero(customHero: CustomHeroDM): Boolean {
        return customHeroDataSource.createCustomHero(customHero.toCustomHeroDto())
    }

    override suspend fun deleteCustomHeroById(heroId: String): Boolean {
        return customHeroDataSource.deleteCustomHeroById(heroId = heroId)
    }
}