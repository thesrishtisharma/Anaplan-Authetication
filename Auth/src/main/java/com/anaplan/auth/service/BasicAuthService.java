package com.anaplan.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;



@Service
public class BasicAuthService {

    @Value("${anaplan.basic_auth.url}")
    private String basicAuthUrl;

    @Value("${anaplan.basic_auth.username}")
    private String username;

    @Value("${anaplan.basic_auth.password}")
    private String password;

    public String basicAuth(){
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try{
            URL url = new URL(basicAuthUrl);
            connection = (HttpURLConnection) url.openConnection();

            //Basic Auth Header
            String authHeader = "Basic " + getBase64Credentials();
            connection.setRequestProperty("Authorization", authHeader);

            //Send request
            connection.setRequestMethod("POST");
            int responseCode = connection.getResponseCode();

            //Read Response
            StringBuilder response = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            if (responseCode == 201) {
                return response.toString();
            } else {
                throw new IOException("Request failed with response code: " + responseCode);
            }
        }
        catch(Exception e){
            return e.toString();
        }
    }

    private String getBase64Credentials() {
        String credentials = username + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    }
}
