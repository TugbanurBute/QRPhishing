package com.phishing.qrPhishing.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PhishingDetectionService {
    private final String ML_MODEL_URL = "http://127.0.0.1:5001/predict";

    public boolean isPhishing(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"url\": \"" + url + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(ML_MODEL_URL, entity, String.class);
        return response.getBody().contains("true");
    }
}
