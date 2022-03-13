package qqq.qqq.app

import android.app.Application
import qqq.qqq.di.AppComponent
import qqq.qqq.di.AppModule
import qqq.qqq.di.DaggerAppComponent

class App : Application() {

    lateinit var appCompanent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appCompanent = DaggerAppComponent.builder().appModule(AppModule(context = this)).build()
    }
}