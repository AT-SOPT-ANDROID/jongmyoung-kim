package org.sopt.at

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import androidx.appcompat.app.AppCompatDelegate


@HiltAndroidApp
class AtSoptApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        setDayMode()
    }

    private fun setDayMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
