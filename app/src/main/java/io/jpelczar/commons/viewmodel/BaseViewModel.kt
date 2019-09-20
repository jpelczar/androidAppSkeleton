package io.jpelczar.commons.viewmodel

import androidx.lifecycle.ViewModel
import io.jpelczar.App
import io.jpelczar.activity.main.MainActivityViewModel
import io.jpelczar.activity.main.MainFragmentViewModel
import io.jpelczar.commons.injection.ViewModelInjector
import kotlinx.coroutines.Deferred

abstract class BaseViewModel : ViewModel() {

    private val asyncOperations: MutableSet<Deferred<Any>> = mutableSetOf()
    private val injector: ViewModelInjector = App.viewModelInjector

    init {
        inject()
    }


    override fun onCleared() {
        super.onCleared()
        asyncOperations.forEach {
            if (!it.isCancelled && !it.isCompleted) {
                it.cancel()
            }
        }
        asyncOperations.clear()
    }

    protected fun executeAsync(task: Deferred<Any>) {
        asyncOperations.add(task)
    }


    /**
     * Use this method to inject ViewModels
     */
    private fun inject() {
        when (this) {
            is MainActivityViewModel -> injector.inject(this)
            is MainFragmentViewModel -> injector.inject(this)
        }
    }
}