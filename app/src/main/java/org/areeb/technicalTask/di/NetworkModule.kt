package org.areeb.technicalTask.di

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.areeb.data.source.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttp(@ApplicationContext context: Context): OkHttpClient {

        return OkHttpClient.Builder()

            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("x-language", "ar")
                    .addHeader(
                        "Authorization",
                        /* if (BuildConfig.DEBUG)
                             "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhZG1pbiI6eyJpZCI6MTV9LCJpc3MiOiJuYXFsYS1hcHAiLCJpYXQiOjE2OTQ3MDUyMTcsImV4cCI6MTY5NzI5NzIxN30.UtQXNgGqMUy3hT-XxrCpI35eIPCauyUecRIzz7m83TQ"
                         else*/
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhODc5ODU0N2YzNmM1ODI5ZTYyMDIyNTE1MzI2ODQxMSIsInN1YiI6IjY1Mjk5ZjlkZjI4ODM4MDJhMjVkNmUyOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.BeHXJXzXvILbyA1uCUdFbtA-fuBh902pep1FZDNGBc0"
                    )

                    .build()

                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {


        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}
