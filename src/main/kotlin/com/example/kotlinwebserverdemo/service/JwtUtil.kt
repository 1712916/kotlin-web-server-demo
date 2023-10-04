package com.example.kotlinwebserverdemo.service

import com.example.kotlinwebserverdemo.entity.RoleEntity
import com.example.kotlinwebserverdemo.entity.UserRoleEntity
import com.example.kotlinwebserverdemo.response.UserResponse
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date
import javax.crypto.spec.SecretKeySpec

class JwtUtil {
     private val jwtKey: String = "jwtKey"

    private val secret = SecretKeySpec(jwtKey.toByteArray(), "HmacSHA256")

    private val expiration: Long = 86400 // 24 hours, you can adjust this as needed

    fun generateToken(user: UserResponse, roles: List<RoleEntity>): String {
        return Jwts.builder()
            .claim("user", user)
            .claim("roles", roles)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration*1000))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    fun extractUsername(token: String): String {
        return extractClaims(token).subject
    }

    private fun extractClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    fun validateToken(token: String): Boolean {
        return !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expirationDate = extractClaims(token).expiration
        return expirationDate.before(Date())
    }
}
