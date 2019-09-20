package io.jpelczar.commons.extension

import io.jpelczar.commons.LoggerDelegate
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

fun String.xPath(xpathStr: String): String? {
    val logger by LoggerDelegate()

    val xpath = XPathFactory.newInstance().newXPath()
    val dbBuilderFactory = DocumentBuilderFactory.newInstance()
    dbBuilderFactory.isNamespaceAware = false
    val dbBuilder = dbBuilderFactory.newDocumentBuilder()
    return try {
        xpath.compile(xpathStr).evaluate(
            dbBuilder.parse(InputSource(StringReader(this))),
            XPathConstants.STRING
        ) as String?
    } catch (e: Exception) {
        logger.error(message = e.message)
        null
    }
}