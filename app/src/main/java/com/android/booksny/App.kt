package com.android.booksny

import android.app.Application
import com.android.booksny.data.di.moduleNetwork
import com.android.booksny.data.di.moduleRepo
import com.android.booksny.data.di.moduleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(
                moduleNetwork,
                moduleRepo,
                moduleViewModel
            ))
        }

    }
}