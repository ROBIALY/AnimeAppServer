package com.robertconstantin.features.heroes.data.mapper

import com.robertconstantin.features.heroes.data.dto.CustomHeroDto
import com.robertconstantin.features.heroes.domain.model.CustomHeroDM

fun CustomHeroDM.toCustomHeroDto() = CustomHeroDto(image, name, skill)
fun CustomHeroDto.toCustomHeroDM() = CustomHeroDM(image, name, skill)