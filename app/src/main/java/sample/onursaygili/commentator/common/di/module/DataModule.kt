package sample.onursaygili.commentator.common.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sample.onursaygili.commentator.reviews.model.remote.ReviewService
import javax.inject.Singleton

@Module
internal object DataModule {

    @Singleton
    @Provides
    @JvmStatic
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(oktHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(oktHttpClient)
            .baseUrl("https://www.getyourguide.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideReviewService(retrofit: Retrofit): ReviewService = retrofit.create(ReviewService::class.java)

}