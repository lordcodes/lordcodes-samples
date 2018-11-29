package com.lordcodes.chatapp.contacts

data class Contact(val id: Int, val name: String)

class ContactsRepository {
    private val contacts = arrayOf(
        Contact(1, "Andrew"),
        Contact(2, "Melanie"),
        Contact(3, "Jane")
    )

    fun findContact(id: Int) = contacts.find { it.id == id }
}