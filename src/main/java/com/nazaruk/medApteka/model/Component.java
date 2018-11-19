package com.nazaruk.medApteka.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "component")
public class Component extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Length(max = 60)
    private String name;

    @NotNull
    @Column(name = "amout")
    private int amout;

    @NotNull
    private int price;
}
