package com.jpstechno.stock_back.services;

import jakarta.mail.MessagingException;

public interface MailService {

    void envoyerOtpCodeParCourriel(String courriel, String otpCode);
}
