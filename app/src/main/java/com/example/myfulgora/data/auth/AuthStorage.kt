package com.example.myfulgora.data.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import net.openid.appauth.AuthState
import org.json.JSONException

class AuthStorage(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_AUTH_STATE = "auth_state_json"
        private const val TAG = "AuthStorage"
    }

    fun save(state: AuthState) {
        prefs.edit().putString(KEY_AUTH_STATE, state.jsonSerializeString()).apply()
    }

    fun load(): AuthState? {
        val json = prefs.getString(KEY_AUTH_STATE, null) ?: return null
        return try {
            AuthState.jsonDeserialize(json)
        } catch (e: JSONException) {
            Log.w(TAG, "Failed to deserialize AuthState", e)
            null
        }
    }

    fun clear() {
        prefs.edit().remove(KEY_AUTH_STATE).apply()
    }
}