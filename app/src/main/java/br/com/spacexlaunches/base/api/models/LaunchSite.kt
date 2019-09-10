package br.com.spacexlaunches.base.api.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchSite(
    @field:Json(name = "site_name")
    val siteName: String?,
    @field:Json(name = "site_name_long")
    val siteNameLong: String?
) : Parcelable
