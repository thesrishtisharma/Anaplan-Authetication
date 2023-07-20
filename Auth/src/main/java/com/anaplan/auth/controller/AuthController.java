package com.anaplan.auth.controller;

import com.anaplan.auth.service.BasicAuthService;
import com.anaplan.auth.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private BasicAuthService basicAuthService;

    @Autowired
    private OAuthService oAuthService;

    @GetMapping("/basic-auth/login")
    public String basicAuth(){
        return basicAuthService.basicAuth();
    }

    @GetMapping("/oauth/step3")
    public String oAuth3(){
        return oAuthService.OAuthStep3();
    }
}
