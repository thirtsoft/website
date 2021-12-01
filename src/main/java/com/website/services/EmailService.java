package com.website.services;

import com.website.dtos.DemandeDto;
import com.website.dtos.EmailDto;
import org.springframework.mail.MailException;

import java.math.BigDecimal;
import java.util.List;

public interface EmailService {

    void sendEmail(EmailDto emailDto) throws MailException;

    void sendMail(DemandeDto demandeDto) throws MailException;

    EmailDto findById(Long id);

    List<EmailDto> findAll();

    List<EmailDto> findTop12ByOrderByCreateDateDesc();

    List<EmailDto> findAllServicesOrderDesc();

    BigDecimal countNumberOfEmailInMonth();

    void delete(Long id);


}
