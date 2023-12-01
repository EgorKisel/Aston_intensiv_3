package com.example.aston_intensiv_3

interface ContactsRepository {
    fun getContacts(): List<Contact>
    fun addContact(contact: Contact)
    fun editContact(contact: Contact)
    fun deleteContact(contact: Contact)
}