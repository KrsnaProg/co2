package com.quantumai.co2.data

import android.content.SharedPreferences
import android.util.Base64
import androidx.core.content.edit
import org.json.JSONObject

class TokenManager(private val prefs: SharedPreferences) {

    fun saveToken(token: String) {
        prefs.edit {
            putString(KEY_TOKEN, token)
                .putString(KEY_CUSTOMER_ID, decodeCustomerIdFromJwt(token))
        }
    }

    fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

    fun getCustomerId(): String? = prefs.getString(KEY_CUSTOMER_ID, null)

    fun clearToken() {
        prefs.edit {
            remove(KEY_TOKEN)
                .remove(KEY_CUSTOMER_ID)
        }
    }

    fun isLoggedIn(): Boolean = getToken() != null

    /** Decodes the JWT payload and looks for the user/customer ID claim. */
    private fun decodeCustomerIdFromJwt(token: String): String? = try {
        val payloadBase64 = token.split(".").getOrNull(1) ?: return null
        val decoded = String(Base64.decode(payloadBase64, Base64.URL_SAFE or Base64.NO_PADDING))
        val json = JSONObject(decoded)
        // Try common .NET / OpenID claim names in order of preference
        json.optString("sub").takeIf { it.isNotBlank() }
            ?: json.optString("nameid").takeIf { it.isNotBlank() }
            ?: json.optString("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier")
                .takeIf { it.isNotBlank() }
            ?: json.optString("userId").takeIf { it.isNotBlank() }
            ?: json.optString("id").takeIf { it.isNotBlank() }
    } catch (e: Exception) {
        null
    }

    companion object {
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_CUSTOMER_ID = "customer_id"
    }
}
