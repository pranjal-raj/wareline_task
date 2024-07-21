package com.example.test.viewmodel;

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.api.RetrofitClient
import com.example.test.models.ImageModelItem
import com.example.test.models.Meme
import com.example.test.models.Response
import com.example.test.repository.MediaCoverageRepository
import retrofit2.Call
import retrofit2.Callback



class MediaCoverageViewModel(val repository: MediaCoverageRepository) : ViewModel() {

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
    }
}
