package uk.co.ergun.polat.jetpackslearning.di

import dagger.Component
import uk.co.ergun.polat.jetpackslearning.model.AnimalApiService

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: AnimalApiService)



}