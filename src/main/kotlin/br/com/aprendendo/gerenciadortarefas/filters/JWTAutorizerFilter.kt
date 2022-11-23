package br.com.aprendendo.gerenciadortarefas.filters

import br.com.aprendendo.gerenciadortarefas.authorization
import br.com.aprendendo.gerenciadortarefas.bearer
import br.com.aprendendo.gerenciadortarefas.entities.User
import br.com.aprendendo.gerenciadortarefas.impls.UserDetailImpl
import br.com.aprendendo.gerenciadortarefas.utils.JWTUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAutorizerFilter(authenticationManager: AuthenticationManager, val jwtUtils: JWTUtils)
    : BasicAuthenticationFilter(authenticationManager) {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = request.getHeader(authorization)

        if (authorizationHeader != null && authorizationHeader.startsWith(bearer)) {
            val authenticated = getAuthentication(authorizationHeader)
            SecurityContextHolder.getContext().authentication = authenticated
        }

        chain.doFilter(request, response)
    }

    private fun getAuthentication(authorization: String): UsernamePasswordAuthenticationToken {
        val token = authorization.substring(7)
        if (jwtUtils.isValidToken(token)) {
            val idString = jwtUtils.getUserId(token)
            if (!idString.isNullOrBlank() && !idString.isNullOrEmpty()) {
                val usuario = User(id = idString.toLong(), nome = "usuario teste", email = "admin@admin.com", senha = "123")
                val userDetailImpl = UserDetailImpl(usuario)
                return UsernamePasswordAuthenticationToken(userDetailImpl, null, userDetailImpl.authorities)
            }
        }
        throw UsernameNotFoundException("Invalid Token")
    }
}