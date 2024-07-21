package com.example.test.api


import com.example.test.models.ImageModelItem
import com.example.test.models.Response
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/gimme/10")
    fun getMediaCoverages(): Call<Response>
}
