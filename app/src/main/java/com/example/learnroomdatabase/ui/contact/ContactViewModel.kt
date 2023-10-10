package com.example.learnroomdatabase.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnroomdatabase.models.Contact
import com.example.learnroomdatabase.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _contacts = MutableLiveData<List<Contact>>(emptyList())
    val contacts: LiveData<List<Contact>> get() = _contacts

    init {
        queryContactsOrderedByFirstName()
    }

    fun upsertContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.upsert(contact)
        }
    }

    fun queryContactsOrderedByFirstName() {
        contactRepository.queryByFirstName().observeForever { contacts ->
            _contacts.value = contacts
        }
    }

    fun queryContactsOrderedByLastName() {
        contactRepository.queryByLastName().observeForever { contacts ->
            _contacts.value = contacts
        }
    }

    fun queryContactsOrderedByPhoneNumber() {
        contactRepository.queryByPhoneNumber().observeForever { contacts ->
            _contacts.value = contacts
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.delete(contact)
        }
    }
}