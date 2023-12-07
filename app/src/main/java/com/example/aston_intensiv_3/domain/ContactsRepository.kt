package com.example.aston_intensiv_3.domain

import com.example.aston_intensiv_3.data.Contact

interface ContactsRepository {
    fun getContacts(): List<Contact>
    fun addContact(contact: Contact)
    fun editContact(contact: Contact)
    fun deleteContact(contact: Contact)
    fun deleteAllContacts()
}