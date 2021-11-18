package com.example.not_bored

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getActivities(@Url url :String): Response<ActivitiesInfo>
}