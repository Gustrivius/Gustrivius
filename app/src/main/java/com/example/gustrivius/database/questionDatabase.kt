package com.example.gustrivius.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gustrivius.Question

@Database(entities = [ Question::class ], version=1)
abstract class questionDatabase : RoomDatabase() {
    abstract fun questionDAO(): questionDAO
}
