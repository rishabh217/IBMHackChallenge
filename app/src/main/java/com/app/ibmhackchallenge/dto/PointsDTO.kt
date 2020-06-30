package com.app.ibmhackchallenge.dto

import com.google.gson.annotations.SerializedName

data class PointsDTO(
    @SerializedName("Calc_power") var power: Map<String, Double>,
    @SerializedName("Wind_speed") var speed: Map<String, Double>
) {
    override fun toString(): String {
        return "$power $speed"
    }

}