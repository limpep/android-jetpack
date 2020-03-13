package uk.co.ergun.polat.jetpackslearning

import uk.co.ergun.polat.jetpackslearning.di.ApiModule
import uk.co.ergun.polat.jetpackslearning.model.AnimalApiService

class ApiModuleTest(val mockService: AnimalApiService): ApiModule() {

    override fun provideAnimalApiService(): AnimalApiService {
        return mockService
    }
}