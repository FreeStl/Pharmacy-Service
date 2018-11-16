package com.nazaruk.medApteka.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "doctor")
public class Doctor extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Length(max = 40)
    private String name;

    @NotNull
    @Length(max = 40)
    private String surname;

    @NotNull
    @Length(max = 40)
    private String midname;
}
