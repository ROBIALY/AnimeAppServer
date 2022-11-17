package com.robertconstantin.features.heroes.domain.service

import com.robertconstantin.features.heroes.domain.model.CustomHeroDM
import com.robertconstantin.features.heroes.domain.model.HeroDM
import com.robertconstantin.features.heroes.domain.repository.IHeroRepository

class HeroServiceImpl(private val repository: IHeroRepository): IHeroService {
    override suspend fun getAllHeroes(): List<HeroDM> {
        return repository.getAllHeroes()
    }

    override suspend fun getAllCustomHeroes(): List<CustomHeroDM> {
        return repository.getAllCustomHeroes()
    }

    override suspend fun createCustomHero(heroImage: String, name: String, skill: String): Boolean {
        return repository.createHero(CustomHeroDM(heroImage, name, skill))
    }

    override suspend fun deleteCustomHeroById(heroId: String): Boolean {
        return repository.deleteCustomHeroById(heroId)
    }
}