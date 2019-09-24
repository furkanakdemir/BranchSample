package net.furkanakdemir.branchsample.di

import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.furkanakdemir.branchsample.BranchApplication
import net.furkanakdemir.branchsample.image.GlideImageLoader
import net.furkanakdemir.branchsample.image.ImageLoader
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: BranchApplication): Context = application

    @Provides
    @Singleton
    internal fun provideImageLoader(context: Context): ImageLoader = GlideImageLoader(context)
}
