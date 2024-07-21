package com.example.test.models

data class ImageModelItem(
    val id: String,
    val title: String,
    val language: String,
    val thumbnail: Thumbnail,
    val mediaType: Int,
    val coverageURL: String,
    val publishedAt: String,
    val publishedBy: String,
    val backupDetails: BackupDetails
)


