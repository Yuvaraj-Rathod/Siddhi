package com.edtech.siddhi.utils

object Validations {
    fun isValidPassword(password : String) : Boolean {
        return password.length>=6
                && password.any { it.isDigit() }
                && password.any { it.isLetter() }
                && password.any { it.isLowerCase() }
                && password.any { !(it.isLetterOrDigit()) }
    }

    fun isValidEmail(email : String) : Boolean {
        return email.isNotBlank() && email.isNotEmpty()
    }

    fun isValidLeetCodeProfile(link: String): Boolean {
        val regex = Regex("^https://leetcode\\.com/[a-zA-Z0-9_-]+/?$")
        return regex.matches(link)
    }
}