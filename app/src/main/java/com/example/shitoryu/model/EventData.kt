package com.example.shitoryu.model

import java.io.Serializable

data class EventData(
    val dayOfMonth: Int = -1,
    val month: Int = -1,
    val year: Int = -1,
    val hour: Int = -1,
    val minute: Int = -1,
    val isCompetition: Int = -1,
    val place: String = "",
    val group: String = ""
) : Serializable
