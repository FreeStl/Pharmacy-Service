package com.nazaruk.medApteka.repository.projections;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface MedicineRepositoryCustom<Medicine> {
    List<Object[]> medicineComponentFullInfo11_13(String medName);
}
