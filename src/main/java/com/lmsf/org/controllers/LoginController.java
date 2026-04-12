package com.lmsf.org.controllers;

import com.lmsf.org.dto.LoginDto;
import com.lmsf.org.dto.TokenResponse;
import com.lmsf.org.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
        return ResponseEntity.ok(loginService.authenticate(loginDto, response, request));
    }

}
