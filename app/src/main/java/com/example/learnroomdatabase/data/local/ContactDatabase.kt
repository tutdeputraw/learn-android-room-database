package com.example.learnroomdatabase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.learnroomdatabase.models.Contact

@Database(
    entities = [Contact::class],
    version = 1,
)
abstract class ContactDatabase : RoomDatabase() {
    abstract val dao: ContactDao
}