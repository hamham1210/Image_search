package com.example.atype.data.api

import java.time.chrono.ChronoLocalDateTime

data class SearchModel(
    var title : String,
    var dateTime: String,
    var url : String,
    var islike:Boolean = false
)
