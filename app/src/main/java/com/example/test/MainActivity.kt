package com.example.test;

import ImageAdapter
import android.os.Bundle
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.models.Thumbnail
import com.example.test.repository.MediaCoverageRepository
import com.example.test.repository.MediaCoverageRepositoryImpl
import com.example.test.viewmodel.MediaCoverageViewModel
import com.example.test.viewmodel.MediaCoverageViewModelProviderFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

//import dagger.hilt.android.AndroidEntryPoint
//import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp
//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter
    private lateinit var viewModel: MediaCoverageViewModel
    private lateinit var repository: MediaCoverageRepository
    private lateinit var factory: MediaCoverageViewModelProviderFactory
    val model by viewModels<MediaCoverageViewModel> {MediaCoverageViewModelProviderFactory(MediaCoverageRepositoryImpl()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ImageAdapter()
        recyclerView.adapter = adapter

        model.mediaCoverages.observe(this, Observer { mediaCoverages ->
            val imageUrls = mediaCoverages.map { it.url}
            adapter.setImageUrls(imageUrls)
        })

        model.fetchMediaCoverages()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            model.fetchMediaCoverages()
        }

    }

}
