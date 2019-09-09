package br.com.spacexlaunches.base.api.models

import com.squareup.moshi.Json

data class Rocket(
    @field:Json(name = "rocket_id")
    val rocketId: String?,
    @field:Json(name = "rocket_name")
    val rocketName: String?,
    @field:Json(name = "rocket_type")
    val rocketType: String?
)
