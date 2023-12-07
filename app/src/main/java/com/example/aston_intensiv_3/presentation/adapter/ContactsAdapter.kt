package com.example.aston_intensiv_3.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_intensiv_3.data.Contact
import com.example.aston_intensiv_3.databinding.ItemContactBinding

class ContactsAdapter(private val onClick: (item: Contact) -> Unit) : ListAdapter<Contact, ContactsAdapter.ContactsViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        val viewHolder = ContactsViewHolder(binding)
            viewHolder.itemView.setOnClickListener {
            val item = getItem(viewHolder.adapterPosition)
                onClick(item)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }

    inner class ContactsViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.firstName.text = "${contact.firstName} ${contact.lastName}"
            binding.phoneNumber.text = contact.phoneNumber
        }
    }

    private class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
}