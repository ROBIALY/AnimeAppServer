package com.robertconstantin.features.heroes.data.datasource.interfaces

import com.robertconstantin.features.heroes.data.dto.HeroDto

interface ILocalListDataSource {
    suspend fun getAllHeroes(): List<HeroDto>
}