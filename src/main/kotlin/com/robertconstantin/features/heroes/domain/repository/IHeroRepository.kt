package com.robertconstantin.features.heroes.domain.repository

import com.robertconstantin.features.heroes.domain.model.CustomHeroDM
import com.robertconstantin.features.heroes.domain.model.HeroDM
import com.robertconstantin.features.heroes.presentation.model.CustomHero

interface IHeroRepository {
    suspend fun getAllHeroes(): List<HeroDM>
    suspend fun getAllCustomHeroes(): List<CustomHeroDM>
    suspend fun createHero(customHero: CustomHeroDM): Boolean
    suspend fun deleteCustomHeroById(heroId: String): Boolean
}