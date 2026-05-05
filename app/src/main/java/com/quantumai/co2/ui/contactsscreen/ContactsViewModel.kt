package com.quantumai.co2.ui.contactsscreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quantumai.co2.domain.usecases.AddFriendUseCase
import com.quantumai.co2.ui.contactsscreen.contact_card_model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AddFriendState {
    object Idle : AddFriendState()
    object Loading : AddFriendState()
    data class Success(val contact: Contact) : AddFriendState()
    data class Error(val message: String) : AddFriendState()
}

class ContactsViewModel(
    private val addFriendUseCase: AddFriendUseCase
) : ViewModel() {

    private val _addFriendState = MutableStateFlow<AddFriendState>(AddFriendState.Idle)
    val addFriendState: StateFlow<AddFriendState> = _addFriendState

    // Persistent list within the ViewModel
    val savedContacts = mutableStateListOf<Contact>()

    fun addFriend(name: String, phoneNumber: String) {
        viewModelScope.launch {
            _addFriendState.value = AddFriendState.Loading
            try {
                addFriendUseCase(nickName = name, phoneNumber = phoneNumber)
                val newContact = Contact(name = name, phone = phoneNumber)
                savedContacts.add(newContact)
                _addFriendState.value = AddFriendState.Success(newContact)
            } catch (e: Exception) {
                _addFriendState.value = AddFriendState.Error(e.message ?: "Network Error")
            }
        }
    }

    fun removeFriend(contact: Contact) {
        savedContacts.remove(contact)
    }

    fun resetState() {
        _addFriendState.value = AddFriendState.Idle
    }
}