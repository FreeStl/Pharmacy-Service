package com.nazaruk.medApteka.model;

import com.nazaruk.medApteka.model.entityEnums.MedClass;
import com.nazaruk.medApteka.model.entityEnums.MedType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicine")
public class Medicine extends  AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MedType medType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "class")
    private MedClass medClass;

    @NotNull
    @Length(max = 60)
    private String name;

    @NotNull
    @Column(name = "amout")
    private int amout;

    @NotNull
    private int price;

    @Column(name = "sold_count")
    private int soldCount;

    @OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            mappedBy = "medicine")
    private Technology technology;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MedType getMedType() {
        return medType;
    }

    public void setMedType(MedType medType) {
        this.medType = medType;
    }

    public MedClass getMedClass() {
        return medClass;
    }

    public void setMedClass(MedClass medClass) {
        this.medClass = medClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmout() {
        return amout;
    }

    public void setAmout(int amout) {
        this.amout = amout;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }
}
