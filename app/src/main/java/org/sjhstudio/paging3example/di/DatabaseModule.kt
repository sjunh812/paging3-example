package org.sjhstudio.paging3example.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sjhstudio.paging3example.data.local.UnsplashDatabase
import org.sjhstudio.paging3example.util.Constants.UNSPLASH_DATABASE
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun getUnSplashDatabase(
        @ApplicationContext context: Context
    ): UnsplashDatabase {
        synchronized(this) {
            return Room.databaseBuilder(
                context,
                UnsplashDatabase::class.java,
                UNSPLASH_DATABASE
            ).build()
        }
    }

}