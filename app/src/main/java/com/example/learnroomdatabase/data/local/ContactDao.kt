package com.example.learnroomdatabase.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.learnroomdatabase.models.Contact

@Dao
interface ContactDao {
//    @Insert
    @Upsert
    fun upsertContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY firstName ASC")
    fun getContactOrderedByFirstName(): LiveData<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY lastName ASC")
    fun getContactOrderedByLastName(): LiveData<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY phoneNumber ASC")
    fun getContactOrderedByPhoneNumber(): LiveData<List<Contact>>
}