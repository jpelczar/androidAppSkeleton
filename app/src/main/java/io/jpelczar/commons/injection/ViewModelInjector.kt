package io.jpelczar.commons.injection

import dagger.Component
import io.jpelczar.activity.main.MainActivityViewModel
import io.jpelczar.activity.main.MainFragmentViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ExampleModule::class])
interface ViewModelInjector {

//    Add methods to inject models
    fun inject(mainActivityViewModel: MainActivityViewModel)
    fun inject(mainFragmentViewModel: MainFragmentViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun exampleModule(service: ExampleModule): Builder
    }

}