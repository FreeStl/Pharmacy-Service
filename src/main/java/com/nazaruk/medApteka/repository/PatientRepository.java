package com.nazaruk.medApteka.repository;


import com.nazaruk.medApteka.model.Patient;
import com.nazaruk.medApteka.model.entityEnums.MedClass;
import com.nazaruk.medApteka.repository.projections.PatientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>,
        PatientRepositoryCustom<Patient>  {
    @Query(value = "SELECT p.* " +
            "FROM ((orders o " +
            "INNER JOIN patient p ON p.id = o.patient_id) " +
            "INNER JOIN medicine m ON m.id = o.medicine_id) " +
            "WHERE (:name IS NULL) OR (m.name = :name) " +
            "AND (:class IS NULL) OR (m.class = :class) " +
            "AND o.created_at BETWEEN :frm AND :till",
            nativeQuery = true)
    List<Patient> patientOrderList5(@Param("name") String name,
                                    @Param("class") String medClass,
                                     @Param("frm") Date from,
                                     @Param("till") Date till
    );

    @Query(value = "SELECT * " +
            "FROM patient " +
            "WHERE number =?1",
            nativeQuery = true)
    Optional<Patient> findByNumber(String number);

}
