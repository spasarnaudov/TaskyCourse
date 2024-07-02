package com.spascoding.taskycourse.feature_auth.presentation.util

class NameInitialsExtractor(val name: String) {

    fun extract(): String {
        return when(wordCount(name)) {
            0 -> throw IllegalArgumentException("Name must contains minimum two letters")
            1 -> extractFromOneWord()
            else -> extractFromMoreThanOneWord()
        }
    }

    private fun extractFromOneWord(): String {
        return "${name[0]}${name[1]}".uppercase()
    }

    private fun extractFromMoreThanOneWord(): String {
        val words = name.split("\\s+".toRegex())
        val firstName = words[0]
        val secondName = words[words.size - 1]
        return "${firstName[0]}${secondName[0]}".uppercase()
    }

    private fun wordCount(str: String): Int {
        val trimmedStr = str.trim()
        return if (trimmedStr.isEmpty()) {
            0
        } else {
            trimmedStr.split("\\s+".toRegex()).size
        }
    }

}