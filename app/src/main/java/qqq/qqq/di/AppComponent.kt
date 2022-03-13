package qqq.qqq.di

import dagger.Component
import qqq.qqq.presentation.MainActivity

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}