package com.robertconstantin.plugins


import com.robertconstantin.features.heroes.domain.service.IHeroService
import com.robertconstantin.features.heroes.presentation.createCustomHero
import com.robertconstantin.features.heroes.presentation.deleteCustomHeroById
import com.robertconstantin.features.heroes.presentation.getAllCustomHeroes
import com.robertconstantin.features.heroes.presentation.getAllHeroes
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject


fun Application.configureRouting() {


    val heroService: IHeroService by inject()
    routing {

        //Hero routes
        getAllHeroes(heroService)
        getAllCustomHeroes(heroService)
        createCustomHero(heroService)
        deleteCustomHeroById(heroService)

        // Access static resources
        static {
            resources("static")
        }
        static("/images") {
            resources("images")
        }
    }
}
