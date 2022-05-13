package org.kotlin.json

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Eishay(
    @SerializedName("images")
    @Json(name = "images")
    val images: List<Image>,
    @SerializedName("media")
    @Json(name = "media")
    val media: Media
)

@JsonClass(generateAdapter = true)
data class Image(
    @SerializedName("height")
    @Json(name = "height")
    val height: Int,
    @SerializedName("size")
    @Json(name = "size")
    val size: String,
    @SerializedName("title")
    @Json(name = "title")
    val title: String,
    @SerializedName("uri")
    @Json(name = "uri")
    val uri: String,
    @SerializedName("width")
    @Json(name = "width")
    val width: Int
)

@JsonClass(generateAdapter = true)
data class Media(
    @SerializedName("bitrate")
    @Json(name = "bitrate")
    val bitrate: Long,
    @SerializedName("duration")
    @Json(name = "duration")
    val duration: Long,
    @SerializedName("format")
    @Json(name = "format")
    val format: String,
    @SerializedName("height")
    @Json(name = "height")
    val height: Int,
    @SerializedName("persons")
    @Json(name = "persons")
    val persons: List<String>,
    @SerializedName("player")
    @Json(name = "player")
    val player: String,
    @SerializedName("size")
    @Json(name = "size")
    val size: Long,
    @SerializedName("title")
    @Json(name = "title")
    val title: String,
    @SerializedName("uri")
    @Json(name = "uri")
    val uri: String,
    @SerializedName("width")
    @Json(name = "width")
    val width: Int
)