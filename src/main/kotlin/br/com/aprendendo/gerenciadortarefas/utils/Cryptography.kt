package br.com.aprendendo.gerenciadortarefas.utils

import org.springframework.stereotype.Component
import java.security.MessageDigest

@Component
class Cryptography {

    fun md5(string: String): String {
        val ByteArray = MessageDigest.getInstance("MD5").digest(string.toByteArray(Charsets.UTF_8))
        return ByteArray.toHex()
    }
    private fun ByteArray.toHex() = joinToString("") {byte -> "%02x".format(byte)}

}