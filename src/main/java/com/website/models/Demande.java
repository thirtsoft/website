package com.website.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "demande")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Demande extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, name = "numero")
    private String numero;

    @Column(length = 100, name = "firstName")
    private String firstName;

    @Column(length = 100, name = "lastName")
    private String lastName;

    @Column(length = 18, name = "mobile")
    private String mobile;

    @Column(length = 90, name = "email")
    private String email;

    @Column(length = 80, name = "typeEtude")
    private String typeEtude;

    @Column(length = 100, name = "directorThese")
    private String directorThese;

    @Column(length = 50, name = "speciality")
    private String speciality;

    @Column(length = 5, name = "masqueSaisi")
    private String masqueSaisi;

    @Column(length = 5, name = "saisiDonnees")
    private String saisiDonnees;

    @Column(length = 5, name = "analyseUnvaried")
    private String analyseUnvaried;

    @Column(length = 5, name = "analyseBivarie")
    private String analyseBivarie;

    @Column(length = 5, name = "analyseMultivariate")
    private String analyseMultivariate;

    @Column(length = 100, name = "subjectThese")
    private String subjectThese;

    @Column(length = 100, name = "baseDeDonnee")
    private String baseDeDonnee;

    @Column(length = 30, name = "price")
    private double price;

    @Column(length = 30, name = "nbreJours")
    private int nbreJours;

    private String subject;

    private String message;

    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date createdDate;
/*
    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private StatusDemande statusDemande;*/

    public Demande(Long id, String numero, String firstName, String lastName,
                   String mobile, String email, String directorThese, String baseDeDonnee,
                   String status,
                   Date createdDate) {
        this.id = id;
        this.numero = numero;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.directorThese = directorThese;
        this.baseDeDonnee = baseDeDonnee;
        this.status = status;
        this.createdDate = createdDate;

    }


}
