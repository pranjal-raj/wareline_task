package com.example.test;

import ImageAdapter
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.models.Thumbnail
import com.example.test.viewmodel.MediaCoverageViewModel

//import dagger.hilt.android.AndroidEntryPoint
//import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp
//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter
    private lateinit var viewModel: MediaCoverageViewModel
//    private lateinit var imageScrollListener: ImageScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ImageAdapter()
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(MediaCoverageViewModel::class.java)
        viewModel.mediaCoverages.observe(this, Observer { mediaCoverages ->
            val imageUrls = mediaCoverages.map { constructImageUrl(it.url) }
            adapter.setImageUrls(imageUrls)
        })

        viewModel.fetchMediaCoverages()

//        imageScrollListener = ImageScrollListener(adapter)
//        recyclerView.addOnScrollListener(imageScrollListener)
    }



    private fun constructImageUrl(url : String): String {

        return url

 }
}
