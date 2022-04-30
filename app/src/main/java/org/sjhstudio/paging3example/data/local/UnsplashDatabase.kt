package org.sjhstudio.paging3example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sjhstudio.paging3example.data.local.dao.UnsplashImageDao
import org.sjhstudio.paging3example.data.local.dao.UnsplashRemoteKeyDao
import org.sjhstudio.paging3example.model.UnsplashImage
import org.sjhstudio.paging3example.model.UnsplashRemoteKey

@Database(entities = [UnsplashImage::class, UnsplashRemoteKey::class], version = 1)
abstract class UnsplashDatabase: RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeyDao(): UnsplashRemoteKeyDao

}