package com.example.shitoryu.model

data class EventData(
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    val hour: Int,
    val minute: Int,
    val isCompetition: Int,
    val place: String,
    val group: String
)
