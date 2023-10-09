package com.example.learnroomdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnroomdatabase.SortType.*
import com.example.learnroomdatabase.data.local.Contact
import com.example.learnroomdatabase.data.local.ContactDao
import com.example.learnroomdatabase.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _contacts = MutableLiveData<List<Contact>>(emptyList())
    val contacts: LiveData<List<Contact>>
        get() = _contacts

    init {
        queryContactsOrderedByFirstName()
    }

    fun queryContactsOrderedByFirstName() {
        viewModelScope.launch {
            val result = contactRepository.queryByFirstName()
            _contacts.value = result.value
        }
    }

    fun queryContactsOrderedByLastName() {
        viewModelScope.launch {
            val result = contactRepository.queryByLastName()
            _contacts.value = result.value
        }
    }

    fun queryContactsOrderedByPhoneNumber() {
        viewModelScope.launch {
            val result = contactRepository.queryByPhoneNumber()
            _contacts.value = result.value
        }
    }

    fun onContactItemClicked(contact: Contact) {}
}