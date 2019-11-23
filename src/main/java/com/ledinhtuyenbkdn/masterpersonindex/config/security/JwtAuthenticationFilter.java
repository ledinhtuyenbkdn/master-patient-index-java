package com.ledinhtuyenbkdn.masterpersonindex.config.security;

import com.ledinhtuyenbkdn.masterpersonindex.service.dto.LoginSuccessDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails userDetails = (User) authResult.getPrincipal();

        String userName = userDetails.getUsername();
        List<String> role = userDetails.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());

        String accessToken = buildJwtToken(userName, role);
        LoginSuccessDTO loginSuccessDTO = new LoginSuccessDTO();
        loginSuccessDTO.setAccessToken(accessToken);

        PrintWriter writer = response.getWriter();
        writer.println(loginSuccessDTO);
        writer.flush();
    }

    private String buildJwtToken(String userName, List<String> role) {
        Key key = Keys.hmacShaKeyFor(SecurityConstants.SECRET_KEY.getBytes());
        Date expirationDate = new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_DURATION);

        return Jwts.builder().signWith(key, SignatureAlgorithm.HS512).setSubject(userName).setExpiration(expirationDate).claim("role", role).compact();
    }
}
