package com.robertconstantin.features.heroes.data.datasource

import com.robertconstantin.features.heroes.data.datasource.interfaces.ICustomHeroDataSource
import com.robertconstantin.features.heroes.data.dto.CustomHeroDto
import org.litote.kmongo.coroutine.CoroutineDatabase

class CustomHeroDatasourceImpl(private val db: CoroutineDatabase): ICustomHeroDataSource {
    private val customHeroCollection = db.getCollection<CustomHeroDto>()

    override suspend fun getAllCustomHeroes(): List<CustomHeroDto> {
        return customHeroCollection.find().toList()
    }

    override suspend fun createCustomHero(customHero: CustomHeroDto): Boolean {
        return customHeroCollection.insertOne(customHero).wasAcknowledged()
    }

    override suspend fun deleteCustomHeroById(heroId: String) =
        customHeroCollection.findOneById(heroId)?.run {
            customHeroCollection.deleteOneById(this.id).wasAcknowledged()
        } ?: kotlin.run { false }

}