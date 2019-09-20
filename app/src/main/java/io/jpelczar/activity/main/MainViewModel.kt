package io.jpelczar.activity.main

import io.jpelczar.commons.injection.ExampleService
import io.jpelczar.commons.viewmodel.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel : BaseViewModel() {

    @Inject
    lateinit var exampleService: ExampleService

    fun execute() = exampleService.execute()

}

class MainFragmentViewModel : BaseViewModel() {

    @Inject
    lateinit var exampleService: ExampleService

    fun execute() = exampleService.execute()
}