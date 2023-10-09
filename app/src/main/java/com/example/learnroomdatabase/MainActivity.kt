package com.example.learnroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnroomdatabase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ContactViewModel
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        setContentView(binding.root)

        setupContactAdapter()

        onRadioButtonChangeListener()

        observeContacts()
    }

    private fun onRadioButtonChangeListener() {
        binding.RBGFilter.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (findViewById<RadioButton>(checkedId).text) {
                "First Name" -> viewModel.queryContactsOrderedByFirstName()
                "Last Name" -> viewModel.queryContactsOrderedByLastName()
                "Phone" -> viewModel.queryContactsOrderedByPhoneNumber()
            }
        }
    }

    private fun setupContactAdapter() {
        contactAdapter = ContactAdapter { viewModel.onContactItemClicked(it) }
        binding.rvContacts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = contactAdapter
        }
    }

    private fun observeContacts() {
        viewModel.contacts.observe(this) {
            println("DISINI LAGI")
            contactAdapter.setContacts(ArrayList(it ?: emptyList()))
        }
    }
}