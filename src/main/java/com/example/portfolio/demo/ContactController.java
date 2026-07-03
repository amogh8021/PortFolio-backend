package com.example.portfolio.demo;




import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService emailService) {
        this.contactService = emailService  ;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> sendMessage(
            @Valid @RequestBody ContactRequest request) {

        contactService.sendContactEmail(request);

        return ResponseEntity.ok(
                Map.of(
                        "success", "true",
                        "message", "Your message has been sent successfully."
                )
        );
    }
}