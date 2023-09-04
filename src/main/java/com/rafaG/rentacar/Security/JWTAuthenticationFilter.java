package com.rafaG.rentacar.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaG.rentacar.Models.User;
import com.rafaG.rentacar.Utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
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
        System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("User: "+user.getEmail());
        System.out.println(new BCryptPasswordEncoder().encode(user.getPassword()));
        System.out.println("Pass: "+user.getPassword());
        System.out.println(usernamePAT.toString());
        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException, NullPointerException {
        JWTUtil jwt = new JWTUtil();

        UserDetailsImp userCred = (UserDetailsImp) authResult.getPrincipal();

        String token = jwt.create(String.valueOf(userCred.getId()),userCred.getUsername());
        System.out.println("User Credenciales: "+userCred.getUsername());
        System.out.println("User ID: "+String.valueOf(userCred.getId()));

        System.out.println("JWT: "+token);
        response.addHeader("Authorization", "Bearer "+token);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }


}
