package com.example.demo

import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


@Service
class EncryptionService {
    private val secretKey = "pgSettle30y739r82jtd709yOfZ2yK5K"

    fun encrypt(data: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)

        val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return Base64.getEncoder().encodeToString(encrypted)
    }

    fun decrypt(data: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)

        val decodedBytes = Base64.getDecoder().decode(data)
        return String(cipher.doFinal(decodedBytes))
    }
}
