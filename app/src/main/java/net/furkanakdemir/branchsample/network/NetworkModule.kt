package net.furkanakdemir.branchsample.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideBranchService(retrofit: Retrofit): BranchService {
        return retrofit.create(BranchService::class.java)
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
        return builder.client(httpClient).build()
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.apply { loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY }
        return loggingInterceptor
    }

    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor = HeaderInterceptor()

    companion object {

        private const val baseUrl = "https://api.foursquare.com//"
    }
}
