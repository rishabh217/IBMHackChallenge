package com.app.ibmhackchallenge.api

import com.app.ibmhackchallenge.dto.PointsList
import retrofit2.Call
import retrofit2.http.GET

interface GetPointsService {

    @GET("/rishabh217/ibmJSON/db")
    fun getAllPoints() : Call<PointsList>
}