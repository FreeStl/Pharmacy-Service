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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
