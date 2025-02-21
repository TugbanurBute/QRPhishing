package com.phishing.qrPhishing.controller;

import com.phishing.qrPhishing.service.PhishingDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // ✅ Allows requests from Flutter Web
public class PhishingController {

    @Autowired
    private PhishingDetectionService phishingService;

    @PostMapping("/scan_qr")
    public Map<String, Boolean> checkPhishing(@RequestBody Map<String, String> request) {
        String url = request.get("url");
        boolean isPhishing = phishingService.isPhishing(url);

        return Map.of("is_phishing", isPhishing);
    }
}
