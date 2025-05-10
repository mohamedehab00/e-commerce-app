package com.ecommerce.app.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
