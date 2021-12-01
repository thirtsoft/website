package com.website.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "email")
public class Email extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    //  private String name;
    private static final String name = "Librairie Al-AMINE";
    private static final String from = "thirdiallo@gmail.com";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    @Lob
    private String message;

    private Date createDate;

    @ManyToOne
    private Demande demande;

    public Email(String receiver, String subject, String message) {
        this.receiver = this.demande.getEmail();
        this.subject = subject;
        this.message = message;
    }

    public Email() {

    }

    public String getReceiver() {
        return receiver;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public String getName() {
        return name;
    }


    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Email{" +
                "recever='" + receiver + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
