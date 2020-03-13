package uk.co.ergun.polat.jetpackslearning

import android.app.Application
import uk.co.ergun.polat.jetpackslearning.di.PrefsModule
import uk.co.ergun.polat.jetpackslearning.util.SharedPreferencesHelper

class PrefModuleTest(val mockPref: SharedPreferencesHelper): PrefsModule() {

    override fun provideSharedPreferences(app: Application): SharedPreferencesHelper {
        return mockPref
    }
}