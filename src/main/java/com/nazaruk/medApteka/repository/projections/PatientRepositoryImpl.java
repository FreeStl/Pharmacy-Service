package com.nazaruk.medApteka.repository.projections;

import com.nazaruk.medApteka.model.entityEnums.MedClass;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class PatientRepositoryImpl<Patient> implements
        PatientRepositoryCustom<Patient> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> patientForgotOrder1() {
        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT p.*, o.updated_at AS order_update, o.id AS order_id\n" +
                        "FROM patient AS p\n" +
                        "INNER JOIN orders o ON p.id = o.patient_id\n" +
                        "WHERE  o.status = 'READY';"
        );

        return nativeQuery.getResultList();
    }

    public List<Object[]> patientWaiting2(){
        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT p.*, m.name\n" +
                        "FROM ((orders AS o\n" +
                        "INNER JOIN patient p ON p.id = o.patient_id)\n" +
                        "INNER JOIN medicine m ON m.id = o.medicine_id)\n" +
                        "WHERE o.status = 'IN_PROD';"
        );
        return nativeQuery.getResultList();
    }

    public List<Object[]> patientWaiting2(MedClass medClass){
        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT p.*, m.name\n" +
                        "FROM ((orders AS o\n" +
                        "INNER JOIN patient p ON p.id = o.patient_id)\n" +
                        "INNER JOIN medicine m ON m.id = o.medicine_id)\n" +
                        "WHERE o.status = 'IN_PROD' AND m.class = :medClass;"
        );
        nativeQuery.setParameter("medClass", medClass);
        return nativeQuery.getResultList();
    }
}
