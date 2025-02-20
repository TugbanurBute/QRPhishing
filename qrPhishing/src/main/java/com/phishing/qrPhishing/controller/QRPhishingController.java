package com.phishing.qrPhishing.controller;

import com.phishing.qrPhishing.service.PhishingDetectionService;
import com.phishing.qrPhishing.model.QRRequest;
import com.phishing.qrPhishing.model.QRResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QRPhishingController {

    @Autowired
    private PhishingDetectionService phishingDetectionService;

    @PostMapping
    public ResponseEntity<?> scanQR(@RequestBody QRRequest qrRequest) {
        boolean isPhishing = phishingDetectionService.isPhishing(qrRequest.getUrl());
        return ResponseEntity.ok(new QRResponse(qrRequest.getUrl(), isPhishing));
    }
}
