package uk.co.ergun.polat.jetpackslearning.di

import dagger.Component
import uk.co.ergun.polat.jetpackslearning.viewmodel.ListViewModel

@Component(modules = [ApiModule::class])
interface ViewModelComponent {


    fun inject(viewModel: ListViewModel)
}