package com.app.admin.security;

import com.app.admin.config.SecurityConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            val jwt = getJwt(request);
            if (jwt != null && tokenProvider.validateToken(jwt)) {
                val auth = tokenProvider.getAuthentication(jwt, request);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            log.error("Can NOT set user authentication -> Message: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwt(HttpServletRequest request) {
        val authHeader = request.getHeader(SecurityConfig.AUTHORIZATION_HEADER);

        if (authHeader != null && authHeader.startsWith(SecurityConfig.HEADER_PREFIX)) {
            return authHeader.replace(SecurityConfig.HEADER_PREFIX, "");
        } else {
            return null;
        }
    }
}
