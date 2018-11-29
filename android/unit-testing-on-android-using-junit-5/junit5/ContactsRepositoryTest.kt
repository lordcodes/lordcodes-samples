package com.lordcodes.ovationtime.contacts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ContactsRepositoryTest {
    private val repository = ContactsRepository()

    @Nested
    @DisplayName("Given valid contact ID")
    inner class ValidContactId  {
        private val id = 2

        @Test
        @DisplayName("When find contact, then correct contact returned")
        fun findContact_givenExistingId() {
            val expectedContact = Contact(id, "Melanie")

            val actualContact = repository.findContact(id)

            assertThat(actualContact).isEqualTo(expectedContact)
        }
    }

    @Nested
    @DisplayName("Given non-existent contact ID")
    inner class InvalidContactId  {
        private val id = 10

        @Test
        @DisplayName("When find contact, then no contact returned")
        fun findContact_givenInvalidId() {
            val actualContact = repository.findContact(id)

            assertThat(actualContact).isNull()
        }
    }
}
