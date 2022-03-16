package qqq.qqq.data.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserNameDao {

    @Insert
    fun saveUserDB(userNameEntity: UserNameEntity)

    @Query("SELECT * FROM Users")
    fun getUserDB(): List<UserNameEntity>
}