package com.nazaruk.medApteka.repository.projections;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class OrdersRepositoryImpl<Orders> implements OrdersRepositoryCustom<Orders> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> ordersInProd8() {
        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT o.id, p.surname AS p_surname, p.name AS p_name, " +
                        "d.surname AS d_surname, d.name AS d_name, " +
                        "r.diagnos AS r_diagnos, m.name, \n" +
                        "o.status, o.updated_at, o.created_at\n" +
                        "    FROM ((((orders AS o \n" +
                        "        INNER JOIN patient p on o.patient_id = p.id)\n" +
                        "        INNER JOIN doctor d ON o.doctor_id = d.id)\n" +
                        "        INNER JOIN medicine m ON o.medicine_id = m.id)\n" +
                        "        INNER JOIN receipt r ON o.receipt_id = r.id)\n" +
                        "    WHERE o.status = 'IN_PROD';"
        );
        return nativeQuery.getResultList();
    }
}
