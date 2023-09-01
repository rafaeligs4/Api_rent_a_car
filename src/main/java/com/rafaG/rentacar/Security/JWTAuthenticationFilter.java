package com.rafaG.rentacar.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaG.rentacar.Models.User;
import com.rafaG.rentacar.Utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
    User user = new User();
    try{
        user = new ObjectMapper().readValue(request.getReader(),User.class);
    }
    catch (IOException e){e.printStackTrace(); }
        UsernamePasswordAuthenticationToken usernamePAT  = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );

    return getAuthenticationManager().authenticate(usernamePAT);
    }

    protected void succesfullAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        User userCred = (User) auth.getPrincipal();
        String jwt = new JWTUtil().create("1212",userCred.getEmail());

        response.addHeader("Authorization", "Barrer "+jwt);
        super.successfulAuthentication(request,response,chain,auth);
    }
}
