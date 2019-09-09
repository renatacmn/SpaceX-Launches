package br.com.spacexlaunches.base.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "article_link")
    val articleLink: String?,
    @Json(name = "flickr_images")
    val flickrImages: List<String>,
    @Json(name = "mission_patch")
    val missionPatch: String?,
    @Json(name = "mission_patch_small")
    val missionPatchSmall: String?,
    @Json(name = "video_link")
    val videoLink: String?,
    @Json(name = "wikipedia")
    val wikipedia: String?,
    @Json(name = "youtube_id")
    val youtubeId: String?
)
