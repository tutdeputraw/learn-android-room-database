package com.example.learnroomdatabase.ui.popupForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.learnroomdatabase.models.Contact
import com.example.learnroomdatabase.databinding.FragmentPopupFormBinding
import com.example.learnroomdatabase.ui.contact.PopupFragmentCallback

class PopupFormFragment(
    private val callback: PopupFragmentCallback<Contact>?
) : DialogFragment() {
    private lateinit var binding: FragmentPopupFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopupFormBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSubmitButtonClicked()
    }

    private fun onSubmitButtonClicked() {
        binding.btnSubmit.setOnClickListener {
            val contact = Contact(
                firstName = binding.edFirstName.text.toString(),
                lastName = binding.edLastName.text.toString(),
                phoneNumber = binding.edPhoneNumber.text.toString()
            )
            callback?.onDataReceivedFromPopupFragment(contact)
            dismiss()
        }
    }
}
