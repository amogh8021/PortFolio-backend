package com.example.portfolio.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class ContactService {

    @Value("${portfolio.mail.to}")
    private String receiverEmail;

    @Value("${resend.api.key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();

    public void sendContactEmail(ContactRequest request) {

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

        Map<String, Object> body = Map.of(
                "from", "Portfolio <onboarding@resend.dev>",
                "to", receiverEmail,
                "subject", "📩 New Portfolio Contact Message",
                "html", html
        );

        restClient.post()
                .uri("https://api.resend.com/emails")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + apiKey)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }
}