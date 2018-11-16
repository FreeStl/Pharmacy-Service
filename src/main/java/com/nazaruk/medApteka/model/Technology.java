package com.nazaruk.medApteka.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "technology")
public class Technology extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    @Length(max = 100)
    private String name;

    @NotNull
    @Length(max = 200)
    private String info;

    @NotNull
    @Length(max = 4)
    private int days;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "component",
            joinColumns = { @JoinColumn(name = "tech_id") },
            inverseJoinColumns = { @JoinColumn(name = "med_id") })
    private Set<Medicine> components = new HashSet<>();

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Set<Medicine> getComponents() {
        return components;
    }

    public void setComponents(Set<Medicine> components) {
        this.components = components;
    }
}
