package net.furkanakdemir.branchsample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.furkanakdemir.branchsample.network.NetworkModule
import net.furkanakdemir.branchsample.ui.MainActivity

@ExperimentalCoroutinesApi
@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class, NetworkModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}
