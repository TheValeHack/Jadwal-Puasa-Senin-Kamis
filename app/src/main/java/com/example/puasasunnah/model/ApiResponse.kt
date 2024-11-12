package com.example.puasasunnah.model

data class ApiResponse(
    val success: Boolean,
    val message: String,
    val data: List<JadwalPuasa>,
    val timestamp: Long
)