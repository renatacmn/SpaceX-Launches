package br.com.spacexlaunches.base.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Launch(
    @Json(name = "details")
    val details: String?,
    @Json(name = "flight_number")
    val flightNumber: Int,
    @Json(name = "launch_date_utc")
    val launchDateUtc: String,
    @Json(name = "launch_failure_details")
    val launchFailureDetails: LaunchFailureDetails,
    @Json(name = "launch_site")
    val launchSite: LaunchSite,
    @Json(name = "launch_success")
    val launchSuccess: Boolean?,
    @Json(name = "links")
    val links: Links,
    @Json(name = "mission_name")
    val missionName: String,
    @Json(name = "rocket")
    val rocket: Rocket
)
