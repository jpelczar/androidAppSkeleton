package io.jpelczar.commons.injection

import dagger.Module
import dagger.Provides
import io.jpelczar.commons.LoggerDelegate

@Module
class ExampleModule {

    @Provides
    fun publicEventService(): ExampleService = ExampleServiceImpl()

}

//FIXME remove example
interface ExampleService {
    fun execute()
}

//FIXME remove example
class ExampleServiceImpl : ExampleService {

    private val logger by LoggerDelegate()

    override fun execute() {
        logger.debug(message = "Example service execute")
    }
}