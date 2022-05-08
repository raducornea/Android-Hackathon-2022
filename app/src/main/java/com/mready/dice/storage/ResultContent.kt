package com.mready.dice.storage
import kotlinx.serialization.*


@Serializable
data class ResultContent(val total: Int, val zar1: Int, val zar2: Int, val dubla: Boolean)
