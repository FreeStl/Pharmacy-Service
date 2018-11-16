package com.nazaruk.medApteka.model;

import com.nazaruk.medApteka.model.entityEnums.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Orders extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "receipt_id", nullable =false)
    private Receipt receipt;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "medicine_id", nullable =false)
    private Medicine medicine;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;


}
