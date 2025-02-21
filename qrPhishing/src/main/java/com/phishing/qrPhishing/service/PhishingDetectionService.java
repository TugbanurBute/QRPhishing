package com.phishing.qrPhishing.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhishingDetectionService {

    public boolean isPhishing(String url) {
        List<String> phishingKeywords = List.of("paypal", "login", "secure", "bank", "account", "verify", "update");
        List<String> suspiciousTlds = List.of(".ru", ".cn", ".tk", ".biz", ".info");

        // Check for phishing keywords
        for (String keyword : phishingKeywords) {
            if (url.toLowerCase().contains(keyword)) {
                return true;
            }
        }

        // Check for suspicious domain endings
        for (String tld : suspiciousTlds) {
            if (url.endsWith(tld)) {
                return true;
            }
        }

        return false;
    }
}
