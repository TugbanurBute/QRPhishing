package com.phishing.qrPhishing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

@CrossOrigin(origins = "*") // Allow web access
@RestController
@RequestMapping("/api")
public class PhishingController {

    @PostMapping("/scan_qr")
    public ResponseEntity<?> scanQRCode(@RequestBody Map<String, String> request) {
        String url = request.get("url");

        // Dummy phishing detection
        boolean isPhishing = checkForPhishing(url);

        return ResponseEntity.ok(Collections.singletonMap("is_phishing", isPhishing));
    }

    private boolean checkForPhishing(String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }

        // Check URL against PhishTank
        if (isUrlInPhishTank(url)) {
            return true;
        }

        return containsSuspiciousKeywords(url) || !isTrustedDomain(url) || checkUrlRedirects(url);
    }


    private boolean isUrlInPhishTank(String url) {
        try {
            String apiUrl = "https://data.phishtank.com/check?url=" + url;

            // 🔹 Ignore SSL verification (TEMPORARY solution)
            TrustManager[] trustAllCertificates = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
                        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
                    }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCertificates, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            URL urlObj = new URL(apiUrl);
            HttpsURLConnection conn = (HttpsURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            return (responseCode == 200); // If response is 200, the URL is phishing
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    // ✅ Step 2: Detect Suspicious Patterns
    private boolean containsSuspiciousKeywords(String url) {
        String[] phishingPatterns = {"login", "verify", "secure", "update", "paypal", "bank", "free", "offer", "gift", "reset"};
        for (String keyword : phishingPatterns) {
            if (url.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    // ✅ Step 3: Check Trusted Domains
    private boolean isTrustedDomain(String url) {
        String[] trustedDomains = {"google.com", "paypal.com", "amazon.com", "apple.com"};
        for (String domain : trustedDomains) {
            if (url.contains(domain)) {
                return true;
            }
        }
        return false;
    }

    // ✅ Step 4: Check for Redirects (Phishing URLs Often Redirect)
    private boolean checkUrlRedirects(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            int statusCode = conn.getResponseCode();
            return (statusCode == HttpURLConnection.HTTP_MOVED_TEMP || statusCode == HttpURLConnection.HTTP_MOVED_PERM);
        } catch (Exception e) {
            return true; // If error, assume it is phishing
        }
    }
}

