package com.example.learnroomdatabase

import android.annotation.SuppressLint
import android.graphics.Movie
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnroomdatabase.data.local.Contact
import com.example.learnroomdatabase.databinding.ItemRowContactBinding

class ContactAdapter(
    private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ListViewHolder>() {
    private var contacts = ArrayList<Contact>()

    @SuppressLint("NotifyDataSetChanged")
    fun setContacts(contacts: ArrayList<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    class ListViewHolder(val binding: ItemRowContactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowContactBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.d("getItemCount", contacts.size.toString())
        return contacts.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val contact = contacts[position]
        holder.binding.name.text = "${contact.firstName} ${contact.lastName}"
        holder.binding.phoneNumber.text = contact.phoneNumber
        holder.itemView.setOnClickListener { onClick(contact) }
    }
}