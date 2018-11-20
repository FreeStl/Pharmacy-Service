package com.nazaruk.medApteka.repository.projections;

import com.nazaruk.medApteka.model.Medicine;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class MedicineRepositoryImpl<Medicine> implements MedicineRepositoryCustom<Medicine> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> medicineComponentFullInfo11_13(String medName) {
        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT m.*, c.*\n" +
                        "FROM ((medicine_component AS mc\n" +
                        "    INNER JOIN medicine m ON mc.med_id = m.id)\n" +
                        "    INNER JOIN component c on mc.comp_id = c.id)\n" +
                        "WHERE m.name =:medName;"
        );
        nativeQuery.setParameter("medName", medName);

        return nativeQuery.getResultList();
    }
}
