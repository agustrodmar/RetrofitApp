package com.example.marsphotos.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class representing a photo from Mars.
 * This class is serializable, which means instances of this class can be serialized to and
 * deserialized from data formats such as JSON.
 *
 * @property id The ID of the Mars photo.
 * @property imgSrc The URL of the Mars photo image. In the JSON, this property is named "img_src".
 */
@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)