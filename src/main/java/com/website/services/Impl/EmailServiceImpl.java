package com.website.services.Impl;

import com.website.dtos.DemandeDto;
import com.website.dtos.EmailDto;
import com.website.exceptions.ResourceNotFoundException;
import com.website.models.Email;
import com.website.repository.EmailRepository;
import com.website.services.DemandeService;
import com.website.services.EmailService;
import com.website.utilis.ConstantsMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    private final DemandeService demandeService;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository,
                            DemandeService demandeService,
                            JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.demandeService = demandeService;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(EmailDto emailDto) throws MailException {
        boolean f = false;

        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + emailDto.getRecipient()).append(System.lineSeparator());
        sb.append("\n Subject : " + emailDto.getSubject());
        sb.append("\n Message : " + emailDto.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(ConstantsMail.to);
        mail.setFrom(emailDto.getCustomerEmail());
        mail.setSubject(emailDto.getSubject());
        mail.setText(emailDto.getMessage());

        emailDto.setCreateDate(new Date());
        emailDto.setCustomerName(emailDto.getCustomerName());

        System.out.println(emailDto);

        javaMailSender.send(mail);

        EmailDto.fromEntityToDto(
                emailRepository.save(
                        EmailDto.fromDtoToEntity(emailDto)
                )
        );

    }

    @Override
    public void sendMail(DemandeDto demandeDto) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(demandeDto.getEmail());
        mail.setFrom(ConstantsMail.from);
        mail.setSubject(demandeDto.getSubject());
        mail.setText(demandeDto.getMessage());

        System.out.println(demandeDto);

        javaMailSender.send(mail);


    }

    @Override
    public EmailDto findById(Long id) {
        if (id == null) {
            return null;
        }

        Optional<Email> optionalEmail = emailRepository.findById(id);

        return Optional.of(EmailDto.fromEntityToDto(optionalEmail.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Email avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<EmailDto> findAll() {
        return emailRepository.findAll().stream()
                .map(EmailDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmailDto> findTop12ByOrderByCreateDateDesc() {
        return emailRepository.findTop12ByOrderByCreateDateDesc().stream()
                .map(EmailDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmailDto> findAllServicesOrderDesc() {
        return emailRepository.findByOrderByIdDesc().stream()
                .map(EmailDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumberOfEmailInMonth() {
        return emailRepository.countNumberOfEmail();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        emailRepository.deleteById(id);
    }
}
