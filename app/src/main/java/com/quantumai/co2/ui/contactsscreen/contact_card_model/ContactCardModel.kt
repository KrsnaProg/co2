package com.quantumai.co2.ui.contactsscreen.contact_card_model

import java.util.UUID

data class Contact(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val phone: String
)
