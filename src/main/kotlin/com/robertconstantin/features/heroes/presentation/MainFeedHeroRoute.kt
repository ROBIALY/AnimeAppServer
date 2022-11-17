package com.robertconstantin.features.heroes.presentation

import com.robertconstantin.common.ApiResponse
import com.robertconstantin.common.Constants.GET_ALL_HEROES
import com.robertconstantin.common.Constants.NO_HEROES_FOUND
import com.robertconstantin.features.heroes.domain.service.IHeroService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllHeroes(
    heroService: IHeroService
) {
    get(GET_ALL_HEROES) {
        heroService.getAllHeroes().also { heroList ->
            if (heroList.isEmpty()) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = emptyList<Unit>(),
                )
                return@get
            }
            call.respond(status = HttpStatusCode.OK, message = heroList)
        }
    }
}