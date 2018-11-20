package com.nazaruk.medApteka.model;

import com.nazaruk.medApteka.model.entityEnums.MedClass;
import com.nazaruk.medApteka.model.entityEnums.MedType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "medicine")
public class Medicine extends  AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private Integer amout;

    @NotNull
    private Integer price;

    @Column(name = "sold_count")
    private Integer soldCount;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "medicine_component",
            joinColumns = { @JoinColumn(name = "med_id") },
            inverseJoinColumns = { @JoinColumn(name = "comp_id") }
    )
    private List<Component> components = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAmout() {
        return amout;
    }

    public void setAmout(Integer amout) {
        this.amout = amout;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
