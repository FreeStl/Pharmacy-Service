package com.nazaruk.medApteka.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "receipt")
public class Receipt extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    private int amout;

    @NotNull
    private String diagnos;

    @NotNull
    private String instruction;


}
