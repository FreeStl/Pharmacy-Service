package com.nazaruk.medApteka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nazaruk.medApteka.model.entityEnums.MedClass;
import com.nazaruk.medApteka.model.entityEnums.MedType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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
    @Column(unique=true)
    private String name;

    @NotNull
    private Integer amount;

    @NotNull
    private Integer price;

    @Column(name = "sold_count")
    private Integer soldCount;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "medicine_elements",
            joinColumns = { @JoinColumn(name = "med_id") },
            inverseJoinColumns = { @JoinColumn(name = "elem_id") }
    )
    private List<Elements> elements = new ArrayList<>();

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public List<Elements> getElements() {
        return elements;
    }

    public void setElements(List<Elements> elements) {
        this.elements = elements;
    }
}
