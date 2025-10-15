package com.tencent.compose.sample

import nl.adaptivity.xmlutil.serialization.XML
import kotlinx.serialization.Serializable

@Serializable
data class Person(val name: String, val age: Int)

class XmlUtilTest {
    companion object {
        private var xmlInstance: XML? = null

        private fun getXmlInstance(): XML? {
            if (xmlInstance == null) {
                try {
                    xmlInstance = XML()
                } catch (e: Exception) {
                    // 在某些平台上（如鸿蒙）可能无法初始化XML实例
                    println("Failed to initialize XML instance: ${e.message}")
                    xmlInstance = null
                }
            }
            return xmlInstance
        }
    }

    fun testXmlSerialization(): String {
        return try {
            val xml = getXmlInstance()
            if (xml != null) {
                val person = Person("张三", 30)
                xml.encodeToString(Person.serializer(), person)
            } else {
                "<error>XML serialization not available</error>"
            }
        } catch (e: Exception) {
            "<error>XML serialization failed: ${e.message}</error>"
        }
    }

    fun testXmlDeserialization(xml: String): Person? {
        return try {
            val xmlInstance = getXmlInstance()
            if (xmlInstance != null && !xml.contains("<error>")) {
                xmlInstance.decodeFromString(Person.serializer(), xml)
            } else {
                null
            }
        } catch (e: Exception) {
            println("XML deserialization failed: ${e.message}")
            null
        }
    }
}
