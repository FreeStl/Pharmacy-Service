package com.nazaruk.medApteka.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.nazaruk.medApteka.model.entityEnums.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "orders")
public class Orders extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, optional = false)
    @JoinColumn(name = "receipt_id", nullable =false)
    private Receipt receipt;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, optional = false)
    @JoinColumn(name = "medicine_id", nullable =false)
    private Medicine medicine;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;

    public Orders() {
    }

    public Orders(Doctor doctor, Patient patient, Receipt receipt, Medicine medicine, @NotNull OrderStatus orderStatus) {
        this.doctor = doctor;
        this.patient = patient;
        this.receipt = receipt;
        this.medicine = medicine;
        this.orderStatus = orderStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) &&
                Objects.equals(doctor, orders.doctor) &&
                Objects.equals(patient, orders.patient) &&
                Objects.equals(receipt, orders.receipt) &&
                Objects.equals(medicine, orders.medicine) &&
                orderStatus == orders.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, patient, receipt, medicine, orderStatus);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", receipt=" + receipt +
                ", medicine=" + medicine +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
