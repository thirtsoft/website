package com.website.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "service")
@Data
//@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Service extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, name = "code")
    private String code;

    @Column(length = 100, name = "name")
    private String name;

    @Column(name = "decription")
    @Lob
    private String description;

    @Column(name = "createdDate")
    private Date createdDate;

    public Service(Long id, String code, String name, String description, Date createdDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
    }
}
