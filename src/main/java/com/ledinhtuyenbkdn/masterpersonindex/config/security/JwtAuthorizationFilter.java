package com.ledinhtuyenbkdn.masterpersonindex.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = getAuthentication(request);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        super.doFilterInternal(request, response, chain);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        String accessToken = authorizationHeader.replace(SecurityConstants.TOKEN_PREFIX, "");
        Key key = Keys.hmacShaKeyFor(SecurityConstants.SECRET_KEY.getBytes());

        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken);

            String userName = claimsJws.getBody().getSubject();
            List<GrantedAuthority> role = ((List<String>) claimsJws.getBody().get("role")).stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(userName, null, role);
        } catch (JwtException exception) {
            System.out.println("Jwt is not validate");
        }
        return null;
    }
}
