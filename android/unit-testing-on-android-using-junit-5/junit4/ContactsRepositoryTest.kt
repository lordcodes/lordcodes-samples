package com.lordcodes.chatapp.contacts

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ContactsRepositoryTest {
    private val repository = ContactsRepository()

    @Test
    fun findContact_invalidId() {
        val id = 10

        val actualContact = repository.findContact(id)

        assertThat(actualContact).isNull()
    }

    @Test
    fun findContact_existingId() {
        val id = 2
        val expectedContact = Contact(id, "Melanie")

        val actualContact = repository.findContact(id)

        assertThat(actualContact).isEqualTo(expectedContact)
    }
}