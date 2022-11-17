package com.robertconstantin.features.heroes.domain.service

import com.robertconstantin.features.heroes.domain.model.CustomHeroDM
import com.robertconstantin.features.heroes.domain.model.HeroDM
import javax.sql.rowset.serial.SerialStruct

interface IHeroService {
    suspend fun getAllHeroes(): List<HeroDM>
    suspend fun getAllCustomHeroes(): List<CustomHeroDM>
    suspend fun createCustomHero(heroImage: String, name: String, skill: String): Boolean
    suspend fun deleteCustomHeroById(heroId: String): Boolean
}