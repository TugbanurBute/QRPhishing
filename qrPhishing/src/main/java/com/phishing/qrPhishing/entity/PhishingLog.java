package com.phishing.qrPhishing.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "phishing_logs")
public class PhishingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private boolean isPhishing;
}
