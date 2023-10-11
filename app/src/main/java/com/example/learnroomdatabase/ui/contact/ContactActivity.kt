package com.example.learnroomdatabase.ui.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnroomdatabase.R
import com.example.learnroomdatabase.models.Contact
import com.example.learnroomdatabase.databinding.ActivityContactBinding
import com.example.learnroomdatabase.ui.contact.adapter.ContactAdapter
import com.example.learnroomdatabase.ui.popupForm.PopupFormFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : AppCompatActivity(), PopupFragmentCallback<Contact> {

    private lateinit var binding: ActivityContactBinding
    private val viewModel: ContactViewModel by viewModels()
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupContactAdapter()

        onRadioButtonChangeListener()

        onFabClicked()

        observeContacts()
    }

    private fun onRadioButtonChangeListener() {
        binding.RBGFilter.setOnCheckedChangeListener { _, checkedId ->
            when (findViewById<RadioButton>(checkedId).text) {
                getString(R.string.first_name) -> viewModel.queryContactsOrderedByFirstName()
                getString(R.string.last_name) -> viewModel.queryContactsOrderedByLastName()
                getString(R.string.phone) -> viewModel.queryContactsOrderedByPhoneNumber()
            }
        }
    }

    private fun setupContactAdapter() {
        contactAdapter = ContactAdapter(
            onDeleteClicked = { viewModel.deleteContact(it) }
        )
        binding.rvContacts.apply {
            layoutManager = LinearLayoutManager(this@ContactActivity)
            adapter = contactAdapter
        }
    }

    private fun observeContacts() {
        viewModel.contacts.observe(this) {
            contactAdapter.setContacts(ArrayList(it ?: emptyList()))
        }
    }

    private fun onFabClicked() {
        binding.floatingActionButton.setOnClickListener {
            val popupFormFragment = PopupFormFragment(this)
            popupFormFragment.show(supportFragmentManager, "PopupFormFragment")
        }
    }

    override fun onDataReceivedFromPopupFragment(data: Contact) {
        viewModel.upsertContact(data)
    }
}


interface PopupFragmentCallback<T> {
    fun onDataReceivedFromPopupFragment(data: T)
}