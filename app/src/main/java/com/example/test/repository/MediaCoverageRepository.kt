package com.example.test.repository

import com.example.test.api.RetrofitClient
import com.example.test.models.ImageModelItem
import com.example.test.models.Response
import retrofit2.Call

interface MediaCoverageRepository {
    fun getMediaCoverages(page: Int): Call<Response>
}
