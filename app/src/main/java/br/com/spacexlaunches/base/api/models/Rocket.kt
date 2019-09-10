package br.com.spacexlaunches.base.api.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rocket(
    @field:Json(name = "rocket_id")
    val rocketId: String?,
    @field:Json(name = "rocket_name")
    val rocketName: String?,
    @field:Json(name = "rocket_type")
    val rocketType: String?
) : Parcelable
