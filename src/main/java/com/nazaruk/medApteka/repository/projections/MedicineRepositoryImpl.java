package com.nazaruk.medApteka.repository.projections;

import com.nazaruk.medApteka.model.Medicine;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class MedicineRepositoryImpl<Medicine> implements
        MedicineRepositoryCustom<Medicine> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> medicineComponentFullInfo11_13(String medName) {
        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT m.id AS m_id, m.created_at AS m_created_at," +
                        " m.updated_at AS m_updated_at, m.name AS m_name," +
                        " m.type AS m_type, m.class AS m_class, m.price AS m_price," +
                        " m.amout AS m_amout, m.sold_count AS m_sold_count, c.*\n" +
                        "FROM ((medicine_component AS mc\n" +
                        "    INNER JOIN medicine m ON mc.med_id = m.id)\n" +
                        "    INNER JOIN component c on mc.comp_id = c.id)\n" +
                        "WHERE m.name =:medName;"
        );
        nativeQuery.setParameter("medName", medName);

        return nativeQuery.getResultList();
    }
}
