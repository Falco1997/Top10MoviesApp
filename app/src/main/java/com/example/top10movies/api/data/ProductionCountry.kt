package com.example.top10movies.api.data

import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @SerializedName("iso_3166_1") val iso_3166_1: String,
    @SerializedName("name")val name: String
)