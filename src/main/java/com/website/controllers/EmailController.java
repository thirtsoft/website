package com.website.controllers;

import com.website.controllers.api.EmailApi;
import com.website.dtos.DemandeDto;
import com.website.dtos.EmailDto;
import com.website.exceptions.ResourceNotFoundException;
import com.website.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
public class EmailController implements EmailApi {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @Override
    public ResponseEntity<EmailDto> sendEmail(EmailDto emailDto) throws MailException {
        emailService.sendEmail(emailDto);
        return new ResponseEntity<>(emailDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DemandeDto> sendMail(DemandeDto demandeDto) throws MailException {
        emailService.sendMail(demandeDto);
        return new ResponseEntity<>(demandeDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmailDto> getEmailById(Long id) throws ResourceNotFoundException {
        EmailDto emailDto = emailService.findById(id);

        return new ResponseEntity<>(emailDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAllEmails() {
        List<EmailDto> emailDtoList = emailService.findAll();

        return new ResponseEntity<>(emailDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAllEmailsOrderDesc() {
        List<EmailDto> emailDtoList = emailService.findAllServicesOrderDesc();

        return new ResponseEntity<>(emailDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfEmailInMonth() {
        return emailService.countNumberOfEmailInMonth();
    }

    @Override
    public ResponseEntity<?> deleteEmail(Long id) {
        emailService.delete(id);

        return ResponseEntity.ok().build();
    }
}
