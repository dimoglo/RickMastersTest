package com.example.rickmasterstestdev.di

import com.example.rickmasterstestdev.data.CamerasRepositoryImpl
import com.example.rickmasterstestdev.data.DoorsRepositoryImpl
import com.example.rickmasterstestdev.data.local.CameraEntity
import com.example.rickmasterstestdev.data.local.DoorEntity
import com.example.rickmasterstestdev.data.remote.ApiService
import com.example.rickmasterstestdev.data.remote.ApiServiceImpl
import com.example.rickmasterstestdev.domain.cameras.CamerasRepository
import com.example.rickmasterstestdev.domain.doors.DoorsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.TypedRealmObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiServiceImpl(
            client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json()
                }
            }
        )
    }
    @Provides
    @Singleton
    fun provideRealm(
        config: RealmConfiguration
    ): Realm {
        val realm = Realm.open(config)
        return realm
    }

    @Provides
    @Singleton
    fun provideConfig(): RealmConfiguration {
        val rawConfig = RealmConfiguration.Builder(
            schema = setOf(
                CameraEntity::class,
                DoorEntity::class,
            ) as Set<KClass<out TypedRealmObject>>
        )
        rawConfig.name("abc")
        rawConfig.deleteRealmIfMigrationNeeded()
        val config = rawConfig.build()
        return config
    }
    @Module
    @InstallIn(ViewModelComponent::class)
    internal abstract class ApiServiceMappingModule {

        @Binds
        abstract fun bindDoorsRepository(impl: DoorsRepositoryImpl): DoorsRepository

        @Binds
        abstract fun bindCamerasRepository(impl: CamerasRepositoryImpl): CamerasRepository

    }

}