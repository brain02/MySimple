package qqq.qqq.presentation.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import qqq.qqq.presentation.di.appModule
import qqq.qqq.presentation.di.dataModule
import qqq.qqq.presentation.di.domainModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(applicationContext)
            modules(dataModule, domainModule, appModule)
        }

    }
}