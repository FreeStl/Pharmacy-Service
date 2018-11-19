package com.nazaruk.medApteka.repository.projections;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class OrdersRepositoryImpl<Orders> implements OrdersRepositoryCustom<Orders> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> OrdersInProd8() {
        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT o.id, p.surname, p.name, d.surname, d.name, m.name, \n" +
                        "o.status, o.updated_at, o.created_at\n" +
                        "    FROM (((orders AS o \n" +
                        "        INNER JOIN patient p on o.patient_id = p.id)\n" +
                        "        INNER JOIN doctor d ON o.doctor_id = d.id)\n" +
                        "        INNER JOIN medicine m ON o.medicine_id = m.id)\n" +
                        "    WHERE o.status = 'IN_PROD';"
        );
        return nativeQuery.getResultList();
    }
}
