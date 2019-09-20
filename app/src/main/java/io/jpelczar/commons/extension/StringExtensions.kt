package io.jpelczar.commons.extension

private const val EMPTY = ""
private const val SPACE = ""

fun String.Companion.empty() = EMPTY
fun String.Companion.space() = SPACE
fun String.remove(vararg toRemove: String): String {
    var result = this
    toRemove.forEach {
        result = result.replace(it, String.empty())
    }
    return result
}


fun String.stripXmlTags() = replace(Regex("<.*?>"), SPACE)