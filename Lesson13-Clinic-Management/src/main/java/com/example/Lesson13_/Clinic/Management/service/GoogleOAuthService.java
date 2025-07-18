package com.example.Lesson13_.Clinic.Management.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


@Service
public class GoogleOAuthService {
    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.redirect.uri}")
    private String redirectUri;

    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    public String getGoogleOAuthUrl() {
        String scope = "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";
        return "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=code"
                + "&scope=" + scope
                + "&access_type=offline";
    }

    public String getAccessToken(String code) throws IOException {
        HttpTransport transport = new NetHttpTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                transport, jsonFactory, clientId, clientSecret, code, redirectUri)
                .execute();

        return tokenResponse.getAccessToken();
    }

    public Map<String, Object> getUserInfo(String accessToken) throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport()
                .createRequestFactory(request -> {
                    request.getHeaders().setAuthorization("Bearer " + accessToken);
                });

        GenericUrl url = new GenericUrl(USER_INFO_URL);
        HttpRequest request = requestFactory.buildGetRequest(url);
        String response = request.execute().parseAsString();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, new TypeReference<>() {
        });
    }
}


