package qqq.qqq.data.storage.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserNameEntity(
    @PrimaryKey(autoGenerate = false)
    val firstName: String,
    val lastName: String,
)