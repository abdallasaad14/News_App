package com.example.newsapp.ui.db

import android.content.Context
import android.provider.MediaStore.Audio.Artists
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsapp.ui.dataClasses.Article

@Database(entities = [Article::class], version = 1)

@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDao: ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null
        fun getInstance(context: Context): ArticleDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java,
                        "articles_data_history"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }

}