package com.robertconstantin.features.heroes.data.datasource.interfaces

import com.robertconstantin.features.heroes.data.dto.CustomHeroDto

interface ICustomHeroDataSource {
    // TODO: getAllCutomHeroes
    suspend fun getAllCustomHeroes(): List<CustomHeroDto>
    suspend fun createCustomHero(customHero: CustomHeroDto): Boolean

    suspend fun deleteCustomHeroById(heroId: String): Boolean
}