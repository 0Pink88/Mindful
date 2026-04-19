package com.example.myapplication.data.model
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "journal_entries")
data class JournalEntry(
    @PrimaryKey(autoGenerate = true)        //should auto generate ids
    val id: Int = 0,
    val date: String = "",
    val content: String = "",
    val complete: Boolean = false
)
