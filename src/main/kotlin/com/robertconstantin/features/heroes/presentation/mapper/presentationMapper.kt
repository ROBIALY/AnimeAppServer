package com.robertconstantin.features.heroes.presentation.mapper

import com.robertconstantin.features.heroes.domain.model.CustomHeroDM
import com.robertconstantin.features.heroes.domain.model.HeroDM
import com.robertconstantin.features.heroes.presentation.model.CustomHero
import com.robertconstantin.features.heroes.presentation.model.Hero

fun HeroDM.toHero() = Hero(id, name, image, about, rating, power, month, day, family, abilities, natureTypes)

