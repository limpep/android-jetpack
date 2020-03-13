package uk.co.ergun.polat.jetpackslearning.model

import io.reactivex.Single
import uk.co.ergun.polat.jetpackslearning.di.DaggerApiComponent
import javax.inject.Inject

class AnimalApiService {

    @Inject
    lateinit var api: AnimalApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getApiKey(): Single<ApiKey> {
        return api.getApiKey()
    }

    fun getAnimals(key: String): Single<List<Animal>> {
        return api.getAnimals(key)
    }

}