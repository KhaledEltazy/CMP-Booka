package org.cmp.cmp_booka

import android.app.Application
import org.cmp.cmp_booka.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@BookApplication)
        }
    }
}