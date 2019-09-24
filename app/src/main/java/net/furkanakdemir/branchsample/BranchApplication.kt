package net.furkanakdemir.branchsample

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.furkanakdemir.branchsample.di.DaggerAppComponent
import javax.inject.Inject

@ExperimentalCoroutinesApi
class BranchApplication : DaggerApplication() {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<DaggerApplication>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}
