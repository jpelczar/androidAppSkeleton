package io.jpelczar

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.jpelczar.commons.injection.DaggerViewModelInjector
import io.jpelczar.commons.injection.ExampleModule
import io.jpelczar.commons.injection.ViewModelInjector

class App : Application() {

    companion object {
        lateinit var viewModelInjector: ViewModelInjector
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        //FIXME remove example
        viewModelInjector = DaggerViewModelInjector.builder()
            .exampleModule(ExampleModule())
            .build()
    }
}