package com.phishing.qrPhishing.model;

public class QRResponse {
    private String url;
    private boolean isPhishing;

    public QRResponse(String url, boolean isPhishing) {
        this.url = url;
        this.isPhishing = isPhishing;
    }

    public String getUrl() {
        return url;
    }

    public boolean isPhishing() {
        return isPhishing;
    }
}
