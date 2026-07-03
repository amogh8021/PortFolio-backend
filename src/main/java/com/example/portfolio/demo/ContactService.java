package com.example.portfolio.demo;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ContactService {

    private final JavaMailSender mailSender;

    @Value("${portfolio.mail.to}")
    private String receiverEmail;

    public ContactService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactEmail(ContactRequest request) {

        System.out.println("========== CONTACT REQUEST RECEIVED ==========");
        System.out.println("Name: " + request.getName());
        System.out.println("Email: " + request.getEmail());

        try {

            System.out.println("Step 1: Creating MimeMessage");

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            System.out.println("Step 2: MimeMessage created");

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            System.out.println("Step 3: MimeMessageHelper created");

            helper.setTo(receiverEmail);
            helper.setSubject("📩 New Portfolio Contact Message");

            System.out.println("Step 4: Receiver and Subject set");

            String currentTime = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm a"));

            String html = """
                    <!DOCTYPE html>
                    <html>
                    <body style="font-family:Arial,sans-serif;background:#f5f5f5;padding:30px;">

                    <div style="
                        max-width:650px;
                        margin:auto;
                        background:white;
                        border-radius:10px;
                        padding:30px;
                        box-shadow:0 5px 20px rgba(0,0,0,0.1);
                    ">

                        <h2 style="color:#c9a227;">
                            ♟ New Portfolio Contact
                        </h2>

                        <hr>

                        <p><strong>Name:</strong></p>
                        <p>%s</p>

                        <p><strong>Email:</strong></p>
                        <p>%s</p>

                        <p><strong>Message:</strong></p>

                        <div style="
                            background:#fafafa;
                            border-left:4px solid #c9a227;
                            padding:15px;
                            margin-top:10px;
                            white-space:pre-wrap;
                        ">
                        %s
                        </div>

                        <br>

                        <p style="color:#777;">
                            Received:
                            %s
                        </p>

                        <hr>

                        <p style="font-size:13px;color:#888;">
                            This message was sent from your Portfolio Contact Form.
                        </p>

                    </div>

                    </body>
                    </html>
                    """.formatted(
                    request.getName(),
                    request.getEmail(),
                    request.getMessage(),
                    currentTime
            );

            helper.setText(html, true);

            System.out.println("Step 5: HTML added");

            System.out.println("Step 6: Sending Email...");

            mailSender.send(mimeMessage);

            System.out.println("Step 7: Email Sent Successfully!");

            System.out.println("=============================================");

        } catch (MessagingException e) {

            System.out.println("MessagingException Occurred");
            e.printStackTrace();

            throw new RuntimeException("Failed to send email.", e);

        } catch (MailException e) {

            System.out.println("MailException Occurred");
            e.printStackTrace();

            throw new RuntimeException("Failed to send email.", e);

        } catch (Exception e) {

            System.out.println("Unknown Exception Occurred");
            e.printStackTrace();

            throw new RuntimeException("Failed to send email.", e);
        }
    }
}