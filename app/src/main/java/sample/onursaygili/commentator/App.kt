package sample.onursaygili.commentator

import dagger.android.support.DaggerApplication
import sample.onursaygili.commentator.common.di.DaggerAppComponent
import sample.onursaygili.commentator.common.di.applyAutoInjector
import sample.onursaygili.commentator.common.di.module.RoomModule
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var appLifecycleCallbacks: AppLifecycleCallbacks

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .roomModule(RoomModule(this))
            .build()

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
        appLifecycleCallbacks.onCreate(this)
    }

    override fun onTerminate() {
        appLifecycleCallbacks.onTerminate(this)
        super.onTerminate()
    }

}