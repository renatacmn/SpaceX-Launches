package br.com.spacexlaunches.base.api.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    @field:Json(name = "article_link")
    val articleLink: String?,
    @field:Json(name = "flickr_images")
    val flickrImages: List<String>?,
    @field:Json(name = "mission_patch")
    val missionPatch: String?,
    @field:Json(name = "mission_patch_small")
    val missionPatchSmall: String?,
    @field:Json(name = "video_link")
    val videoLink: String?,
    @field:Json(name = "wikipedia")
    val wikipedia: String?,
    @field:Json(name = "youtube_id")
    val youtubeId: String?
) : Parcelable
