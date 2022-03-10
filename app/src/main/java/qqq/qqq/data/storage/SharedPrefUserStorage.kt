package qqq.qqq.data.storage

import android.content.Context

private const val SHARED_PREFS_NAME = "shared_name"
private const val FIRST_NAME = "first_name"
private const val LAST_NAME = "last_name"
private const val DEFAULT_NAME = "ФАМИЛИЯ"

class SharedPrefUserStorage(context: Context): UserStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(userData: User):Boolean {
        sharedPreferences.edit().putString(FIRST_NAME, userData.firstName).apply()
        sharedPreferences.edit().putString(LAST_NAME, userData.lastName).apply()
        return true
    }

    override fun get(): User {
        val firstName = sharedPreferences.getString(FIRST_NAME, "") ?: ""
        val lastName = sharedPreferences.getString(LAST_NAME, DEFAULT_NAME) ?: DEFAULT_NAME
        return User(firstName = firstName, lastName = lastName)
    }
}