package com.example.data.di

import com.example.data.services.PokemonService
import com.example.data.utils.ConstantData.Generals.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson{
        return GsonBuilder()
            .create()
    }

    @Provides
    fun provideOkHttpClient() =
        OkHttpClient
            .Builder()
            .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providePokemonService (retrofit: Retrofit): PokemonService{
        return retrofit.create(PokemonService::class.java)
    }


}