package com.quantumai.co2.ui.contactsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.quantumai.co2.R
import com.quantumai.co2.ui.colors.AppColors
import com.quantumai.co2.ui.components.CO2Button
import com.quantumai.co2.ui.components.CO2InputField
import com.quantumai.co2.ui.contactsscreen.contact_card_model.Contact

@Composable
fun ContactsScreen(
    navController: NavController,
    viewModel: ContactsViewModel
){
    ContactsContent()
}

@Composable
fun ContactsContent() {
    var contactName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    val contacts = remember { mutableStateListOf<Contact>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.primaryBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = AppColors.primaryLightBlue,
                    shape = RoundedCornerShape(
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .statusBarsPadding()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = stringResource(R.string.contacts_feature_add_new_contact),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.primaryText
                )

                Spacer(modifier = Modifier.height(15.dp))

                CO2InputField(
                    textFieldModifier = Modifier.height(53.dp),
                    label = stringResource(R.string.contacts_feature_contact_name),
                    value = contactName,
                    onValueChange = { contactName = it },
                    placeholder = stringResource(R.string.contacts_feature_contact_name_place_holder)
                )

                Spacer(modifier = Modifier.height(12.dp))

                CO2InputField(
                    textFieldModifier = Modifier.height(53.dp),
                    label = stringResource(R.string.contacts_feature_phone_number),
                    value = phoneNumber,
                    onValueChange = { input ->
                        phoneNumber = input.filter { it.isDigit() }
                    },
                    placeholder = stringResource(R.string.contacts_feature_phone_number_place_holder),
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(12.dp))

                CO2Button(
                    text = stringResource(R.string.contacts_feature_add_friends),
                    onClick = {
                        if (contactName.isNotBlank() && phoneNumber.isNotBlank()) {
                            val newContact = Contact(name = contactName, phone = phoneNumber)
                            contacts.add(newContact)
                            contactName = ""
                            phoneNumber = ""
                        }
                    }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
        ) {
            Text(
                text = stringResource(R.string.contacts_feature_saved_friends),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.primaryText,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(
                    items = contacts,
                    key = { it.id }
                ) { contact ->
                    FriendCard(
                        name = contact.name,
                        phoneNumber = contact.phone,
                        onDeleteClick = {
                            contacts.remove(contact)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FriendCard(
    name: String,
    phoneNumber: String,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = AppColors.primaryGray,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.friend_icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = AppColors.friendIconColor
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.primaryText
                )
                Text(
                    text = phoneNumber,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = AppColors.secondaryText
                )
            }

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.remove_icon),
                    contentDescription = null,
                    tint = AppColors.removeIconColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
