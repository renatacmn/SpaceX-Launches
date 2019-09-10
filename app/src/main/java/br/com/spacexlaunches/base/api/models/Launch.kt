package br.com.spacexlaunches.base.api.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Launch(
    @field:Json(name = "details")
    val details: String?,
    @field:Json(name = "launch_date_utc")
    val launchDateUtc: String?,
    @field:Json(name = "launch_site")
    val launchSite: LaunchSite?,
    @field:Json(name = "launch_success")
    val launchSuccess: Boolean?,
    @field:Json(name = "links")
    val links: Links?,
    @field:Json(name = "mission_name")
    val missionName: String?,
    @field:Json(name = "rocket")
    val rocket: Rocket?,
    @field:Json(name = "upcoming")
    val upcoming: Boolean?
) : Parcelable
