package com.example.test.viewmodel;

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.api.RetrofitClient
import com.example.test.models.ImageModelItem
import com.example.test.models.Meme
import com.example.test.models.Response
import retrofit2.Call
import retrofit2.Callback



class MediaCoverageViewModel : ViewModel() {

    private val _mediaCoverages = MutableLiveData<List<Meme>>()
    val mediaCoverages: LiveData<List<Meme>> = _mediaCoverages

    init {
        fetchMediaCoverages()
    }

    fun fetchMediaCoverages() {

        RetrofitClient.instance.getMediaCoverages().enqueue(object : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    _mediaCoverages.value = response.body()?.memes
                } else {
                    Log.e("MediaCoverageViewModel", "Failed to fetch media coverages")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("MediaCoverageViewModel", "Failed to fetch media coverages\nError : ${t.message}")            }
        })

//        RetrofitClient.instance.getMediaCoverages().enqueue(object : Callback<List<ImageModelItem>> {
//            override fun onResponse(call: Call<List<ImageModelItem>>, response: Response<List<ImageModelItem>>) {
//                if (response.isSuccessful) {
//                    _mediaCoverages.value = response.body()
//                } else {
//                    Log.e("MediaCoverageViewModel", "Failed to fetch media coverages")
//                }
//            }
//
//            override fun onFailure(call: Call<List<ImageModelItem>>, t: Throwable) {
//                Log.e("MediaCoverageViewModel", "Failed to fetch media coverages", t)
//            }
//        })
    }
}
