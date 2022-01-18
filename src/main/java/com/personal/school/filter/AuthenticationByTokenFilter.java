package com.personal.school.filter;

import com.personal.school.model.User;
import com.personal.school.service.TokenService;
import com.personal.school.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {

    private static final String HEADER_AUTH = "Authorization";
    private static final int START_TOKEN = 7;

    private TokenService tokenService;
    private UserService userService;

    public AuthenticationByTokenFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        Long id = tokenService.isValidToken(token);

        if(nonNull(id)) {
            authClient(id);
        }

        filterChain.doFilter(request, response);
    }

    private void authClient(Long id) {
        User user = userService.getById(id).get();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String getToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_AUTH);
        return isBlank(token) || isNotBearerType(token) ? null : token.substring(START_TOKEN);
    }

    private boolean isNotBearerType(String token) {
        return !token.startsWith("Bearer ");
    }

}
