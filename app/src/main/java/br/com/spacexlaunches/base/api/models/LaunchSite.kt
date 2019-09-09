package br.com.spacexlaunches.base.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchSite(
    @Json(name = "site_id")
    val siteId: String,
    @Json(name = "site_name")
    val siteName: String,
    @Json(name = "site_name_long")
    val siteNameLong: String
)
