package com.jpstechno.stock_back.serviceImplementation;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jpstechno.stock_back.services.MailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailsImplementation implements MailService {

    private final JavaMailSender mailSender;

    public MailsImplementation(JavaMailSender mailSender) {
        this.mailSender = mailSender;

    }

    @Override
    public void envoyerOtpCodeParCourriel(String courriel, String otpCode) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setTo(courriel);
            messageHelper.setSubject("Stock experts - votre code OTP");

            String corpsDuMessage = """
                    <html>
                        <body>
                            <p> votre code OTP est :</p>
                            <h1>%s</h1>
                            <p>Ce code expire dans 5 minutes</p>
                        </body>

                    </html>

                        """.formatted(otpCode);
            messageHelper.setText(corpsDuMessage, true);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Echec lors de l'envoi du message");
        }

    }

}
