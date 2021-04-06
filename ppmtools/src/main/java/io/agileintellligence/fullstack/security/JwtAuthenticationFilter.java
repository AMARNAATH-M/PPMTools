package io.agileintellligence.fullstack.security;

import io.agileintellligence.fullstack.domain.User;
import io.agileintellligence.fullstack.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

import static io.agileintellligence.fullstack.security.SecurityConstants.HEADER_STRING;
import static io.agileintellligence.fullstack.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletrequest, HttpServletResponse httpServletresponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = getJWTFromRequest(httpServletrequest);
            if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt) ) {
                Long userId = jwtTokenProvider.getUserIdFromJWT(jwt);
                User userDetails = customUserDetailsService.loadUserById(userId);

                UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(
                        userDetails,null,(Collections.emptyList()));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletrequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        } catch (Exception ex) {
            logger.error("Could not Set user authentication in security context",ex);

        }
        filterChain.doFilter(httpServletrequest,httpServletresponse);
    }

    private String getJWTFromRequest(HttpServletRequest request)
    {
        String bearerToken = request.getHeader(HEADER_STRING);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}
