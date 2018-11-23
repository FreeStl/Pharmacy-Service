package com.nazaruk.medApteka.repository;


import com.nazaruk.medApteka.model.Patient;
import com.nazaruk.medApteka.repository.projections.PatientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>,
        PatientRepositoryCustom<Patient>  {
    @Query(value = "SELECT p.* " +
            "FROM ((orders o " +
            "INNER JOIN patient p ON p.id = o.patient_id) " +
            "INNER JOIN medicines m ON m.id = o.medicine_id) " +
            "WHERE m.name = :name AND o.created_at BETWEEN :frm AND :till;",
            nativeQuery = true)
    List<Patient> PatientOrderList5(@Param("name") String name,
                                     @Param("frm") Date from,
                                     @Param("till") Date till);

    @Query(value = "SELECT * " +
            "FROM patient " +
            "WHERE number =?1",
            nativeQuery = true)
    Patient findByNumber(String number);

}
