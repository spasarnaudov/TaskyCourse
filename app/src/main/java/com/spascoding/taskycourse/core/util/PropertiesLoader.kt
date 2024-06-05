package com.spascoding.taskycourse.core.util

import java.io.FileNotFoundException
import java.io.InputStream
import java.util.Properties

abstract class PropertiesLoader(private val fileName: String) {
    private val properties: Properties = Properties()

    init {
        val inputStream: InputStream? = javaClass.classLoader.getResourceAsStream(fileName)
        if (inputStream != null) {
            properties.load(inputStream)
        } else {
            throw FileNotFoundException("Property file '$fileName' not found in the classpath")
        }
    }

    fun getProperty(key: String): String {
        return properties.getProperty(key)
    }
}

object ConfigProperties: PropertiesLoader("config.properties")