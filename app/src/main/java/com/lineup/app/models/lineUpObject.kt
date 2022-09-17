package com.lineup.app.models

import com.lineup.app.utils.getToday

data class lineUpObject(
    var name: String,
    var year: String,
    var platform: String,
    var genre: String,
    var description: String,
    var url: String,
    var addedDate: String = getToday()
)
