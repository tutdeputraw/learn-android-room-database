package com.example.learnroomdatabase.domain.repository

import androidx.lifecycle.LiveData
import com.example.learnroomdatabase.models.Contact

interface ContactRepository {
    suspend fun upsert(contact: Contact)
    suspend fun delete(contact: Contact)
     fun queryByFirstName(): LiveData<List<Contact>>
     fun queryByLastName(): LiveData<List<Contact>>
     fun queryByPhoneNumber(): LiveData<List<Contact>>
}
