package br.com.spacexlaunches.base.api

import br.com.spacexlaunches.base.api.models.Launch
import retrofit2.http.GET

interface SpaceXApi {

    @GET("v3/launches")
    suspend fun getAllLaunches(): List<Launch>

}