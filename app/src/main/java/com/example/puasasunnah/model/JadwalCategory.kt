package com.example.puasasunnah.model

import com.google.gson.annotations.SerializedName

data class JadwalCategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
