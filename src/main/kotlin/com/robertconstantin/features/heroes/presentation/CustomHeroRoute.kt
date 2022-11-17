package com.robertconstantin.features.heroes.presentation

import com.google.gson.Gson
import com.robertconstantin.common.ApiResponse
import com.robertconstantin.common.Constants.BASE_URL
import com.robertconstantin.common.Constants.CREATE_CUSTOM_HERO
import com.robertconstantin.common.Constants.HERO_DATA
import com.robertconstantin.common.Constants.HERO_PICTURE_PATH
import com.robertconstantin.features.heroes.domain.model.CustomHeroDM
import com.robertconstantin.features.heroes.domain.service.IHeroService
import com.robertconstantin.features.heroes.presentation.model.CustomHero
import com.robertconstantin.features.heroes.presentation.model.CustomHeroIdRequest
import com.robertconstantin.features.heroes.presentation.util.save
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.io.File

fun Route.createCustomHero(heroService: IHeroService) {

    val gson by inject<Gson>()

    post(CREATE_CUSTOM_HERO) {
        val multipart = call.receiveMultipart()
        var customHero: CustomHero? = null
        var heroImageFileName: String? = null

        multipart.forEachPart { partData ->
            when (partData) {
                is PartData.FormItem -> {
                    if (partData.name == HERO_DATA)
                        customHero = gson.fromJson(partData.value, CustomHero::class.java)
                }

                is PartData.FileItem -> {
                    heroImageFileName = partData.save(HERO_PICTURE_PATH)
                }

                else -> Unit
            }
        }

        customHero?.let { heroData ->
            heroService.createCustomHero(
                heroImage = "${BASE_URL}hero_pictures/$heroImageFileName",
                name = heroData.name,
                skill = heroData.skill
            ).also { heroWasCreated ->
                if (heroWasCreated) {
                    call.respond(
                        status = HttpStatusCode.OK,
                        message = ApiResponse<Unit>(successful = true)
                    )
                } else {
                    //delete the file we created and respond with internalServerError
                    File("${HERO_PICTURE_PATH}/$heroImageFileName").delete()
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
        } ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
    }
}


fun Route.getAllCustomHeroes(heroService: IHeroService) {
    get("/api/customHero/get") {
        heroService.getAllCustomHeroes().also { customHeroList ->
            if (customHeroList.isEmpty()) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = ApiResponse<Unit>(successful = true, message = "There are no custom heroes!")
                )
                return@get
            }
            call.respond(
                status = HttpStatusCode.OK,
                message = ApiResponse(successful = true, data = customHeroList)
            )
        }
    }
}

fun Route.deleteCustomHeroById(heroService: IHeroService) {
    post("/api/customHero/delete") {

        val customHeroId = call.parameters["customHeroId"] ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

//        val request = kotlin.runCatching { call.receiveNullable<CustomHeroIdRequest>() }.getOrNull() ?: kotlin.run {
//            call.respond(HttpStatusCode.BadRequest)
//            return@post
//        }

        if (heroService.deleteCustomHeroById(customHeroId))
            call.respond(HttpStatusCode.OK, ApiResponse<Unit>(successful = true))
        else call.respond(HttpStatusCode.NotFound, ApiResponse<Unit>(successful = false))

    }
}