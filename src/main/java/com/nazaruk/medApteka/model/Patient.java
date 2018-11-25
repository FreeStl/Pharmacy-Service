package com.nazaruk.medApteka.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

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
    @Max(value = 120)
    private Integer age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMidname() {
        return midname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                Objects.equals(name, patient.name) &&
                Objects.equals(surname, patient.surname) &&
                Objects.equals(midname, patient.midname) &&
                Objects.equals(number, patient.number) &&
                Objects.equals(adress, patient.adress) &&
                Objects.equals(age, patient.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, midname, number, adress, age);
    }
}
