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

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "customerEmail")
    private String customerEmail;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    @Lob
    private String message;

    private Date createDate;

    @ManyToOne
    private Demande demande;

    public Email(String recipient, String subject, String message) {
        this.recipient = this.demande.getEmail();
        this.subject = subject;
        this.message = message;
    }

    public Email(String customerName, String recipient, String subject, String message) {
        this.customerName = customerName;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
        this.createDate = new Date();
    }

    public Email() {

    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Email{" +
                "customerName='" + customerName + '\'' +
                "recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
