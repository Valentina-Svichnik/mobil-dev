package com.example.lab1

import androidx.annotation.VisibleForTesting

object UserHolder {

    private val map = mutableMapOf<String, User>()
    private val phoneFormat = Regex("""^[+][\d]{11}""")

    fun registerUser(
        fullName: String,
        email: String,
        password: String
    ): User = User.makeUser(fullName, email = email, password = password)
        .also { user ->
            if (map.containsKey(user.login)) throw IllegalArgumentException("A user with this email already exists")
            else map[user.login] = user
        }

    fun loginUser(login: String, password: String): String? {
        val phoneLogin = cleanPhone(login)
        return if (phoneLogin.isNotEmpty()) {
            map[phoneLogin]
        } else {
            map[login.trim()]
        }?.let {
            if (it.checkPassword(password)) it.userInfo
            else null
        }
    }

    fun registerUserByPhone(fullName: String, rawPhone: String): User = User.makeUser(fullName = fullName, phone = rawPhone)
        .also { user ->
            if (map.containsKey(user.phone))
                throw IllegalArgumentException("Great! Phone is correct.")
            if (cleanPhone(rawPhone).matches("^\\+?[0-9]{11}\$".toRegex()))
                map[user.login] = user
            else
                throw IllegalArgumentException("Sorry, the phone number is incorrect.")
        }

    fun requestAccessCode(login: String) {
        val phone = cleanPhone(login)
        val user = map[phone];
        if (user != null) {
            val accessCode = user.generateAccessCode()
            user.passwordHash = user.encrypt(accessCode)
            user.accessCode = accessCode;
            user.sendAccessCodeToUser(phone,accessCode)
            map[phone] = user
        }
    }

//    ****************

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder() {
        map.clear()
    }

    private fun cleanPhone(phone: String): String {
        return phone.replace("""[^+\d]""".toRegex(), "")
    }
}