package com.nazaruk.medApteka.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

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

    public Doctor() {
    }

    public Doctor(@NotNull @Length(max = 40) String name, @NotNull @Length(max = 40) String surname,
                  @NotNull @Length(max = 40) String midname) {
        this.name = name;
        this.surname = surname;
        this.midname = midname;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id &&
                Objects.equals(name, doctor.name) &&
                Objects.equals(surname, doctor.surname) &&
                Objects.equals(midname, doctor.midname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, midname);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", midname='" + midname + '\'' +
                '}';
    }
}
