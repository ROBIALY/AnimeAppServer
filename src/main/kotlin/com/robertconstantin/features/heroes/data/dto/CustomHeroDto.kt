package com.robertconstantin.features.heroes.data.dto

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class CustomHeroDto(
    val image: String,
    val name: String,
    val skill: String,
    @BsonId
    val id: String = ObjectId().toString()
)
