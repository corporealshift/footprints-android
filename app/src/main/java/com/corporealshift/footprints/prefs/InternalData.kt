package com.corporealshift.footprints.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.corporealshift.footprints.models.Creds
import com.corporealshift.footprints.ui.LoginScreenModel

const val InternalPrefsFile = "footprints.txt"

class InternalData {
    fun saveLoginCredentials(context: Context, loginScreenModel: LoginScreenModel) {
        with (getSharedPrefs(context).edit()) {
            // Edit the user's shared preferences...
            putString("host", loginScreenModel.host)
            putString("username", loginScreenModel.username)
            putString("password", loginScreenModel.password)
            apply()
        }
    }

    fun getCredentials(context: Context): Creds? {
        val prefs = getSharedPrefs(context)
        val username = prefs.getString("username", "")
        val pw = prefs.getString("password", "")
        username ?: return null
        pw ?: return null

        return Creds(username, pw)
    }

    fun getHostDomain(context: Context): String? {
        val prefs = getSharedPrefs(context)
        return prefs.getString("host", null)
    }

    private fun getSharedPrefs(context: Context): SharedPreferences {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
        return EncryptedSharedPreferences.create(
            InternalPrefsFile,
            mainKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}