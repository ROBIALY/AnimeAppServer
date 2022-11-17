package com.robertconstantin.features.heroes.data.mapper

import com.robertconstantin.features.heroes.data.dto.HeroDto
import com.robertconstantin.features.heroes.domain.model.HeroDM

fun HeroDto.toHeroDM() = HeroDM(
    id, name, image, about, rating, power, month, day, family, abilities, natureTypes
)