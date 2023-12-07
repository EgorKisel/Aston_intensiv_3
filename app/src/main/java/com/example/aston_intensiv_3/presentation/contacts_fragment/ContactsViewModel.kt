package com.example.aston_intensiv_3.presentation.contacts_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aston_intensiv_3.data.Contact
import com.example.aston_intensiv_3.data.ContactsRepositoryImpl

class ContactsViewModel() : ViewModel() {

    private val contactRepo = ContactsRepositoryImpl()
    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    fun setContacts(contacts: List<Contact>) {
        _contacts.value = contacts
    }

    init {
        loadContacts()
    }

    private fun loadContacts() {
        _contacts.value = contactRepo.getContacts()
    }

    fun addContact(contact: Contact) {
        val currentContacts = _contacts.value.orEmpty().toMutableList()
        currentContacts.add(contact)
        _contacts.postValue(currentContacts)
    }

    fun editContact(contact: Contact) {
        contactRepo.editContact(contact)
        loadContacts()
    }

    fun deleteContact(contact: Contact) {
        contactRepo.deleteContact(contact)
        loadContacts()
    }

    fun deleteAll() {
        contactRepo.deleteAllContacts()
        loadContacts()
    }
}