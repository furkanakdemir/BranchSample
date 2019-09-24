package net.furkanakdemir.branchsample.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.furkanakdemir.branchsample.BranchApplication
import net.furkanakdemir.branchsample.network.NetworkModule
import net.furkanakdemir.branchsample.ui.BranchModule
import net.furkanakdemir.branchsample.viewmodel.ViewModelModule
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class,
        AppModule::class, ActivityBuilderModule::class, ViewModelModule::class,
        NetworkModule::class, BranchModule::class]
)
interface AppComponent : AndroidInjector<BranchApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<BranchApplication>
}
