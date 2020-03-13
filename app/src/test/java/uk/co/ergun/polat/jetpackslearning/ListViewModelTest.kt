package uk.co.ergun.polat.jetpackslearning

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import uk.co.ergun.polat.jetpackslearning.di.AppModule
import uk.co.ergun.polat.jetpackslearning.di.DaggerViewModelComponent
import uk.co.ergun.polat.jetpackslearning.model.Animal
import uk.co.ergun.polat.jetpackslearning.model.AnimalApiService
import uk.co.ergun.polat.jetpackslearning.util.SharedPreferencesHelper
import uk.co.ergun.polat.jetpackslearning.viewmodel.ListViewModel
import java.util.concurrent.Executor

class ListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var animalService: AnimalApiService

    @Mock
    lateinit var prefs: SharedPreferencesHelper

    val application = Mockito.mock(Application::class.java)

    var listViewModel = ListViewModel(application, true)

    private val key = "Test key"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        DaggerViewModelComponent
            .builder()
            .appModule(AppModule(application))
            .apiModule(ApiModuleTest(animalService))
            .prefsModule(PrefModuleTest(prefs))
            .build()
            .inject(listViewModel)
    }

    @Test
    fun getAnimalSuccess() {
        Mockito.`when`(prefs.getApiKey()).thenReturn(key)
        
        val animal = Animal("cow",
            null,
            null,
            null,
            null,
            null,
            null)

        val animalList = listOf(animal)
        val testSingle = Single.just(animalList)

        Mockito.`when`(animalService.getAnimals(key)).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1, listViewModel.animals.value?.size)
        Assert.assertEquals(false, listViewModel.loadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(
                    Executor { it.run() },
                    true
                )
            }
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }
}