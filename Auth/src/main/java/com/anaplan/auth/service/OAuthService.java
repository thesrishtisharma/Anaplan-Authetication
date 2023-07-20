package com.anaplan.auth.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthService {

    @Value("${anaplan.oauth.url}")
    private String url;

    @Value("${anaplan.oauth.client_id}")
    private String clientId;

    @Value("${anaplan.oauth.grant_type_step3}")
    private String grantType3;

    @Value("${anaplan.oauth.refresh_token}")
    private String refreshToken;

    public String OAuthStep3(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        RestTemplate restTemplate = new RestTemplate();
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("client_id", clientId);
        requestBody.addProperty("grant_type", grantType3);
        requestBody.addProperty("refresh_token", refreshToken);
        Gson gson = new Gson();
        HttpEntity<String> httpEntity = new HttpEntity<>(gson.toJson(requestBody), httpHeaders);
        return restTemplate.postForObject(url, httpEntity, String.class);
    }
}
