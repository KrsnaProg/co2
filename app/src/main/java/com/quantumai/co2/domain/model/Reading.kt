package com.quantumai.co2.domain.model

data class Reading(
    val type: String,
    val unit: String,
    val value: Int
)