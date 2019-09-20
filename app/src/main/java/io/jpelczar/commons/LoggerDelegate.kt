package io.jpelczar.commons


import android.util.Log
import io.jpelczar.commons.extension.empty
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LoggerDelegate : ReadOnlyProperty<Any?, Logger> {

    companion object {
        private fun <T> createLogger(clazz: Class<T>?): Logger {
            return if (clazz != null)
                LoggerFactory.getLogger(clazz) else
                LoggerFactory.getLogger(LoggerDelegate::class.java)
        }
    }

    private var logger: Logger? = null

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): Logger {
        if (logger == null) {
            logger = createLogger(thisRef?.javaClass)
        }
        return logger!!
    }
}


private const val TAG = "APP"


abstract class Logger(private val clazz: Class<*>) {

    fun verbose(
        tag: String? = TAG,
        message: String? = String.empty(),
        e: Throwable? = null
    ) = Log.v(createTag(tag), message, e)

    fun debug(
        tag: String? = TAG,
        message: String? = String.empty(),
        e: Throwable? = null
    ) = Log.d(createTag(tag), message, e)

    fun info(
        tag: String? = TAG,
        message: String? = String.empty(),
        e: Throwable? = null
    ) = Log.i(createTag(tag), message, e)

    fun warn(
        tag: String? = TAG,
        message: String? = String.empty(),
        e: Throwable? = null
    ) = Log.w(createTag(tag), message, e)

    fun error(
        tag: String? = TAG,
        message: String? = String.empty(),
        e: Throwable? = null
    ) = Log.e(createTag(tag), message, e)

    private fun createTag(tag: String?) =
        "[${clazz.canonicalName?.split(".")?.map {
            if (it == clazz.simpleName) it else it[0]
        }?.joinToString(".")}]${if (tag.isNullOrBlank()) String.empty() else "[$tag]"}"

}


object LoggerFactory {

    internal fun getLogger(clazz: Class<*>): Logger = LoggerWrapper(clazz)

    internal class LoggerWrapper(clazz: Class<*>) : Logger(clazz)
}
