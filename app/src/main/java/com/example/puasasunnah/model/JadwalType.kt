package com.example.puasasunnah.model

import com.google.gson.annotations.SerializedName

data class JadwalType(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("background_color")
    val backgroundColor: String,
    @SerializedName("text_color")
    val textColor: String
)
