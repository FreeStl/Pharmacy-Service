package com.nazaruk.medApteka.repository.projections;

import com.nazaruk.medApteka.model.entityEnums.MedClass;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface PatientRepositoryCustom<Patient> {
    List<Object[]> patientForgotOrder1();

    List<Object[]> patientWaiting2();
    List<Object[]> patientWaiting2(MedClass medClass);
}
