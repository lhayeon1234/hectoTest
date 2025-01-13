package com.example.demo

import org.springframework.stereotype.Service
import java.security.MessageDigest

@Service
class HashService {
    private val hashKey = "ST1009281328226982205"

    fun generateHash(data: String): String {
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = messageDigest.digest(data.toByteArray(Charsets.UTF_8))
        return hashedBytes.joinToString("") { "%02x".format(it) }
    }
}
