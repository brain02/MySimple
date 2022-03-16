package qqq.qqq.app

import android.app.Application
import androidx.room.Room
import qqq.qqq.data.storage.room.AppDataBase

class App : Application() {

    companion object {
        var db: AppDataBase? = null
        fun getDatabase(): AppDataBase? {
            return db
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "database"
        )
            .allowMainThreadQueries()
            .build()
    }
}