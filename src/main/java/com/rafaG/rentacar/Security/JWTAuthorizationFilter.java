package com.rafaG.rentacar.Security;

import com.rafaG.rentacar.Utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        String barreerToken = request.getHeader("Authorization");
        if(barreerToken != null && barreerToken.startsWith("Barrer")){
            String token = barreerToken.replace("Barrer","");
            UsernamePasswordAuthenticationToken usernamePat =new JWTUtil().getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(usernamePat);

        }
        chain.doFilter(request,response);
    }
}
