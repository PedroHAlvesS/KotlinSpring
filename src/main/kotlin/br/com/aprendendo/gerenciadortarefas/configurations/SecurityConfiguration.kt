package br.com.aprendendo.gerenciadortarefas.configurations

import br.com.aprendendo.gerenciadortarefas.filters.JWTAutorizerFilter
import br.com.aprendendo.gerenciadortarefas.repositories.UserRepository
import br.com.aprendendo.gerenciadortarefas.utils.JWTUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy


@Configuration
@EnableWebSecurity
class SecurityConfiguration: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var jwtUtils: JWTUtils

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .anyRequest().authenticated()

        http.addFilter(JWTAutorizerFilter(authenticationManager(), jwtUtils, userRepository))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
}