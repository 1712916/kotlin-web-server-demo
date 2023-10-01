package com.example.kotlinwebserverdemo

import com.example.kotlinwebserverdemo.service.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtTokenFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        print("LOL: JwtTokenFilter")
//        val headerAuthorization = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (isEmpty(headerAuthorization) || !headerAuthorization.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // Get jwt token and validate
//        val token = headerAuthorization.split(" ")[1].trim();
//        if (!JwtUtil().validateToken(token)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
        filterChain.doFilter(request, response)

    }
}

//@Configuration
//@EnableWebSecurity
//class SecurityConfig {
//    @Bean
//    fun setFilterChains(http: HttpSecurity): SecurityFilterChain {
//        // Put your endpoint to create/sign, otherwise spring will secure it as
//        // well you won't be able to do any request
//        http.authorizeHttpRequests {
//            it
//                .requestMatchers("/").hasAnyRole()
//                .requestMatchers("/user").denyAll()
//        }
//
//        return http.build()
//    }
//
//}
