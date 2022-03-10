package qqq.qqq.data.storage.sharedpref

import android.content.Context
import qqq.qqq.data.storage.UserStorage
import qqq.qqq.data.storage.models.User

private const val SHARED_PREFS_NAME = "shared_name"
private const val FIRST_NAME = "first_name"
private const val LAST_NAME = "last_name"
private const val DEFAULT_FIRST_NAME = "ИМЯ"
private const val DEFAULT_LAST_NAME = "ФАМИЛИЯ"

class SharedPrefUserStorage(context: Context) : UserStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(userData: User): Boolean {
        sharedPreferences.edit().apply {
            putString(FIRST_NAME, userData.firstName)
            putString(LAST_NAME, userData.lastName)
        }.apply()
        return true
    }

    override fun get(): User {
        val firstName =
            sharedPreferences.getString(FIRST_NAME, DEFAULT_FIRST_NAME) ?: DEFAULT_FIRST_NAME
        val lastName =
            sharedPreferences.getString(LAST_NAME, DEFAULT_LAST_NAME) ?: DEFAULT_LAST_NAME
        return User(firstName = firstName, lastName = lastName)
    }
}