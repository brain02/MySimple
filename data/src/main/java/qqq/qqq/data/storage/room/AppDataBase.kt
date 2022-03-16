package qqq.qqq.data.storage.room

import androidx.room.*

@Database(entities = [UserNameEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dao(): UserNameDao
}