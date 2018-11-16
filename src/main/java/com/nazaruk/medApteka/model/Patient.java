package com.nazaruk.medApteka.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "patient")
public class Patient extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    @Length(max = 40)
    private String name;

    @NotNull
    @Length(max = 40)
    private String surname;

    @NotNull
    @Length(max = 40)
    private String midname;

    @NotNull
    @Length(max = 40)
    private String number;

    @NotNull
    @Length(max = 200)
    private String adress;

    @NotNull
    @Length(max = 3)
    private int age;
}
