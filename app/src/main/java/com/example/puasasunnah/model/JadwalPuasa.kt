package com.example.puasasunnah.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class JadwalPuasa(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("type_id")
    val typeId: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("month")
    val month: Int,
    @SerializedName("day")
    val day: Int,
    @SerializedName("human_date")
    val humanDate: String,
    @SerializedName("category")
    val category: JadwalCategory,
    @SerializedName("type")
    val type: JadwalType
)
