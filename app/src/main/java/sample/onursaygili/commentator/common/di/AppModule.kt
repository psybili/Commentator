package sample.onursaygili.commentator.common.di

import dagger.Module
import dagger.Provides
import sample.onursaygili.commentator.AppLifecycleCallbacks
import sample.onursaygili.commentator.common.di.module.DataModule
import javax.inject.Singleton

@Module(includes = [DataModule::class])
internal object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = DebugAppLifecycleCallbacks()

}