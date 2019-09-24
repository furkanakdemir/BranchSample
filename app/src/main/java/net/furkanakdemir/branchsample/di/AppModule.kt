package net.furkanakdemir.branchsample.di

import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.furkanakdemir.branchsample.BranchApplication
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: BranchApplication): Context = application
}
