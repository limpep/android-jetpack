package uk.co.ergun.polat.jetpackslearning.di

import dagger.Component
import uk.co.ergun.polat.jetpackslearning.viewmodel.ListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, PrefsModule::class, AppModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)


}