package br.com.aprendendo.gerenciadortarefas.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component

@Component
class JWTUtils {

    private val securityKey = "MinhaChaveUltraSecreta"

    fun generateToken(idUser: String): String {
        return Jwts.builder()
                .setSubject(idUser)
                .signWith(SignatureAlgorithm.HS512, securityKey.toByteArray())
                .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claims = getClaimsToken(token)

        if (claims != null) {
            val idUser = claims.subject
            if (!idUser.isNullOrBlank() && !idUser.isNullOrEmpty()) {
                return true
            }
        }
        return false
    }

    private fun getClaimsToken(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(securityKey.toByteArray()).parseClaimsJws(token).body
        } catch (exception: Exception) {
            null
        }

    }

    fun getUserId(token: String): String? {
        val claims = getClaimsToken(token)
        return claims?.subject
    }

}