package com.example.gustrivius.database

import androidx.room.Query
import com.example.gustrivius.Question
import java.util.*

interface questionDAO {
    @Query("SELECT * FROM question")
    suspend fun getCrimes(): List<Question>
    @Query("SELECT * FROM question WHERE id=(:id)")
    suspend fun getCrime(id: UUID): Question
}