package com.example.learnroomdatabase.domain.repository

import androidx.lifecycle.LiveData
import com.example.learnroomdatabase.data.local.Contact

interface ContactRepository {
    suspend fun upsert(contact: Contact)
    suspend fun delete(contact: Contact)
    suspend fun queryByFirstName(): LiveData<List<Contact>>
    suspend fun queryByLastName(): LiveData<List<Contact>>
    suspend fun queryByPhoneNumber(): LiveData<List<Contact>>
}
