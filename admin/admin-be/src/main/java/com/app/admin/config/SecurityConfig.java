package com.app.admin.config;

import com.app.admin.data.user.UserRole;
import com.app.admin.security.AuthEntryPoint;
import com.app.admin.security.TokenFilter;
import com.app.admin.security.TokenProvider;
import lombok.SneakyThrows;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final AuthEntryPoint unauthorizedHandler;
    private final TokenProvider tokenProvider;

    public final static String AUTHORIZATION_HEADER = "Authorization";
    public final static String HEADER_PREFIX = "Bearer ";

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenFilter authenticationJwtTokenFilter() {
        return new TokenFilter(tokenProvider);
    }

    @SneakyThrows
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    @SneakyThrows
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @SneakyThrows
    @Override
    public void configure(HttpSecurity http) {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/login", "/**/sign_up").permitAll()
                .antMatchers("/**").hasAnyRole(UserRole.USER, UserRole.ADMIN)
            .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
