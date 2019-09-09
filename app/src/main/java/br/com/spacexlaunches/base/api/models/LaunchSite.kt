package br.com.spacexlaunches.base.api.models

import com.squareup.moshi.Json

data class LaunchSite(
    @field:Json(name = "site_id")
    val siteId: String?,
    @field:Json(name = "site_name")
    val siteName: String?,
    @field:Json(name = "site_name_long")
    val siteNameLong: String?
)
