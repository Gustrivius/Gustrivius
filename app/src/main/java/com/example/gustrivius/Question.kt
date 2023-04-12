package com.example.gustrivius

import androidx.room.*
import java.util.*

@Entity
data class Question(@PrimaryKey val id: UUID, val QuestionText: String, val Answer: String, val wrongAnswer1: String, val wrongAnswer2: String, val wrongAnswer3: String) {

}