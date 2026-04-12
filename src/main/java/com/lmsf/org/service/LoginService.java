package com.lmsf.org.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lmsf.org.dto.LoginDto;
import com.lmsf.org.dto.TokenResponse;
import com.lmsf.org.entity.UserInfo;
import com.lmsf.org.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public TokenResponse authenticate(LoginDto loginDto, HttpServletResponse response, HttpServletRequest request) throws UsernameNotFoundException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e){
            throw new UsernameNotFoundException("Bad Credentials");
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getUsername());

        final String accessToken = jwtService.generateAccessToken(userDetails);
        final String refreshToken = jwtService.generateRefreshToken(userDetails);

        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,X-Pingother,Origin,X-Requested-With,Content-Type,Accept,X-Custom-header");
        response.setHeader(HEADER_STRING, TOKEN_PREFIX + accessToken);

        return TokenResponse
                        .builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
    }
}
