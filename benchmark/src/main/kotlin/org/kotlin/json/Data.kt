package org.kotlin.json

import com.dslplatform.json.CompiledJson
import com.dslplatform.json.JsonAttribute
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@CompiledJson
data class Eishay(
    @SerializedName("images")
    @Json(name = "images")
    @JsonAttribute(name = "images")
    val images: List<Image>,
    @SerializedName("media")
    @Json(name = "media")
    @JsonAttribute(name = "media")
    val media: Media
)

@JsonClass(generateAdapter = true)
@CompiledJson
data class Image(
    @SerializedName("height")
    @Json(name = "height")
    @JsonAttribute(name = "height")
    val height: Int,
    @SerializedName("size")
    @Json(name = "size")
    @JsonAttribute(name = "size")
    val size: String,
    @SerializedName("title")
    @Json(name = "title")
    @JsonAttribute(name = "title")
    val title: String,
    @SerializedName("uri")
    @Json(name = "uri")
    @JsonAttribute(name = "uri")
    val uri: String,
    @SerializedName("width")
    @Json(name = "width")
    @JsonAttribute(name = "width")
    val width: Int
)

@JsonClass(generateAdapter = true)
@CompiledJson
data class Media(
    @SerializedName("bitrate")
    @Json(name = "bitrate")
    @JsonAttribute(name = "bitrate")
    val bitrate: Long,
    @SerializedName("duration")
    @Json(name = "duration")
    @JsonAttribute(name = "duration")
    val duration: Long,
    @SerializedName("format")
    @Json(name = "format")
    @JsonAttribute(name = "format")
    val format: String,
    @SerializedName("height")
    @Json(name = "height")
    @JsonAttribute(name = "height")
    val height: Int,
    @SerializedName("persons")
    @Json(name = "persons")
    @JsonAttribute(name = "persons")
    val persons: List<String>,
    @SerializedName("player")
    @Json(name = "player")
    @JsonAttribute(name = "player")
    val player: String,
    @SerializedName("size")
    @Json(name = "size")
    @JsonAttribute(name = "size")
    val size: Long,
    @SerializedName("title")
    @Json(name = "title")
    @JsonAttribute(name = "title")
    val title: String,
    @SerializedName("uri")
    @Json(name = "uri")
    @JsonAttribute(name = "uri")
    val uri: String,
    @SerializedName("width")
    @Json(name = "width")
    @JsonAttribute(name = "width")
    val width: Int
)