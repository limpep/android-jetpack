package uk.co.ergun.polat.jetpackslearning.di

import android.app.Application
import dagger.Module
import dagger.Provides
import uk.co.ergun.polat.jetpackslearning.util.SharedPreferencesHelper
import javax.inject.Singleton

@Module
class PrefsModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferencesHelper {
     return SharedPreferencesHelper(app)
    }
}