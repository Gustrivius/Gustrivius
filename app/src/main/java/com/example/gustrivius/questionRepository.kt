package com.example.gustrivius

import android.content.Context
import androidx.room.Room
import com.example.gustrivius.database.questionDatabase
import java.util.*

private const val DATABASE_NAME = "question-database"

class questionRepository private constructor(context: Context) {

    private val database: questionDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            questionDatabase::class.java,
            DATABASE_NAME
        )
        .build()

    suspend fun getQuestions(): List<Question> = database.questionDAO().getCrimes()
    suspend fun getQuestion(id: UUID): Question = database.questionDAO().getCrime(id)

    companion object {
        private var INSTANCE: questionRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = questionRepository(context)
            }
        }
        fun get(): questionRepository {
            return INSTANCE ?:
            throw IllegalStateException("questionRepository must be initialized")
        }
    }
}
