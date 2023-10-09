package com.example.learnroomdatabase.data.repository

import androidx.lifecycle.LiveData
import com.example.learnroomdatabase.data.local.Contact
import com.example.learnroomdatabase.data.local.ContactDao
import com.example.learnroomdatabase.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val contactDao: ContactDao
) : ContactRepository {
    override suspend fun upsert(contact: Contact) {
        contactDao.upsertContact(contact)
    }

    override suspend fun delete(contact: Contact) {
        contactDao.deleteContact(contact)
    }

    override suspend fun queryByFirstName(): LiveData<List<Contact>> {
        return contactDao.getContactOrderedByFirstName()
    }

    override suspend fun queryByLastName(): LiveData<List<Contact>> {
        return contactDao.getContactOrderedByLastName()
    }

    override suspend fun queryByPhoneNumber(): LiveData<List<Contact>> {
        return contactDao.getContactOrderedByPhoneNumber()
    }
}