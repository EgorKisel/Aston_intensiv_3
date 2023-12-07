package com.example.aston_intensiv_3.data

import com.example.aston_intensiv_3.domain.ContactsRepository

class ContactsRepositoryImpl: ContactsRepository {

    private val contactsList = mutableListOf<Contact>()

    override fun getContacts(): List<Contact> = contactsList.toList()

    override fun addContact(contact: Contact) {
        contactsList.add(contact)
    }

    override fun editContact(contact: Contact) {
        val index = contactsList.indexOfFirst { it.id == contact.id }
        if (index != -1) {
            contactsList[index] = contact
        }
    }

    override fun deleteContact(contact: Contact) {
        contactsList.removeAll { it.id == contact.id }
    }

    override fun deleteAllContacts() {
        contactsList.clear()
    }
}