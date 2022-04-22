package org.sjhstudio.paging3example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.sjhstudio.paging3example.model.UnsplashImage
import org.sjhstudio.paging3example.model.UnsplashRemoteKey

@Database(entities = [UnsplashImage::class, UnsplashRemoteKey::class], version = 1)
abstract class UnsplashDatabase: RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeyDao(): UnsplashRemoteKeyDao

    companion object {

        private var instance: UnsplashDatabase? = null

        fun getInstance(context: Context): UnsplashDatabase? {
            if(instance == null) {
                // 다중스레드에서 접근방지 = lock
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UnsplashDatabase::class.java,
                        "unsplash_db"
                    ).build()
                }
            }

            return instance
        }

    }

}