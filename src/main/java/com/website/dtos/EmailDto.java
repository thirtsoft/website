package com.website.dtos;


import com.website.models.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private Long id;

    private String receiver;

    private String subject;

    private String message;

    private Date createDate;

    private DemandeDto demandeDto;

    public static EmailDto fromEntityToDto(Email email) {
        if (email == null) {
            return null;
        }

        return EmailDto.builder()
                .id(email.getId())
                .receiver(email.getReceiver())
                .subject(email.getSubject())
                .message(email.getMessage())
                .createDate(email.getCreateDate())
                .demandeDto(DemandeDto.fromEntityToDto(email.getDemande()))
                .build();
    }

    public static Email fromDtoToEntity(EmailDto emailDto) {
        if (emailDto == null) {
            return null;
        }

        Email email = new Email();
        email.setId(emailDto.getId());
        email.setReceiver(emailDto.getReceiver());
        email.setSubject(emailDto.getSubject());
        email.setMessage(emailDto.getMessage());
        email.setCreateDate(emailDto.getCreateDate());
        email.setDemande(DemandeDto.fromDtoToEntity(emailDto.getDemandeDto()));

        return email;
    }
}
