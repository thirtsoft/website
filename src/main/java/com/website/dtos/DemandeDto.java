package com.website.dtos;


import com.website.models.Demande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemandeDto {

    private Long id;

    private String numero;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String typeEtude;

    private String directorThese;

    private String speciality;

    private String masqueSaisi;

    private String saisiDonnees;

    private String analyseUnvaried;

    private String analyseBivarie;

    private String analyseMultivariate;

    private String subjectThese;

    private String baseDeDonnee;

    private double price;

    private int nbreJours;

    private String subject;

    private String message;

    private String status;

    private Date createdDate;

   /* @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private StatusDemande statusDemande; */

    public static DemandeDto fromEntityToDto(Demande demande) {
        if (demande == null) {
            return null;
        }

        return DemandeDto.builder()
                .id(demande.getId())
                .numero(demande.getNumero())
                .firstName(demande.getFirstName())
                .lastName(demande.getLastName())
                .mobile(demande.getMobile())
                .email(demande.getEmail())
                .typeEtude(demande.getTypeEtude())
                .directorThese(demande.getDirectorThese())
                .speciality(demande.getSpeciality())
                .masqueSaisi(demande.getMasqueSaisi())
                .saisiDonnees(demande.getSaisiDonnees())
                .analyseUnvaried(demande.getAnalyseUnvaried())
                .analyseBivarie(demande.getAnalyseBivarie())
                .analyseMultivariate(demande.getAnalyseMultivariate())
                .subjectThese(demande.getSubjectThese())
                .baseDeDonnee(demande.getBaseDeDonnee())
                .price(demande.getPrice())
                .nbreJours(demande.getNbreJours())
                .status(demande.getStatus())
                .createdDate(demande.getCreatedDate())
                .build();
    }

    public static Demande fromDtoToEntity(DemandeDto demandeDto) {
        if (demandeDto == null) {
            return null;
        }

        Demande demande = new Demande();
        demande.setId(demandeDto.getId());
        demande.setNumero(demandeDto.getNumero());
        demande.setFirstName(demandeDto.getFirstName());
        demande.setLastName(demandeDto.getLastName());
        demande.setMobile(demandeDto.getMobile());
        demande.setEmail(demandeDto.getEmail());
        demande.setTypeEtude(demandeDto.getTypeEtude());
        demande.setDirectorThese(demandeDto.getDirectorThese());
        demande.setSpeciality(demandeDto.getSpeciality());
        demande.setMasqueSaisi(demandeDto.getMasqueSaisi());
        demande.setSaisiDonnees(demandeDto.getSaisiDonnees());
        demande.setAnalyseUnvaried(demandeDto.getAnalyseUnvaried());
        demande.setAnalyseBivarie(demandeDto.getAnalyseBivarie());
        demande.setAnalyseMultivariate(demandeDto.getAnalyseMultivariate());
        demande.setSubjectThese(demandeDto.getSubjectThese());
        demande.setBaseDeDonnee(demandeDto.getBaseDeDonnee());
        demande.setPrice(demandeDto.getPrice());
        demande.setNbreJours(demandeDto.getNbreJours());
        demande.setStatus(demandeDto.getStatus());
        demande.setCreatedDate(demandeDto.getCreatedDate());

        return demande;
    }
}
