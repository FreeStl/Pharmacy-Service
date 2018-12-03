package com.nazaruk.medApteka.repository;

import com.nazaruk.medApteka.model.Elements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementsRepository extends JpaRepository<Elements, Integer> {
    @Query(value = "SELECT * " +
            "FROM elements AS e " +
            "WHERE e.id = ANY (SELECT me.elem_id " +
            "   FROM(( medicine m " +
            "       INNER JOIN orders o ON m.id = o.medicine_id) " +
            "       INNER JOIN medicine_elements me ON m.id = me.med_id) " +
            "   WHERE o.status = 'IN_PROD')",
            nativeQuery = true)
    List<Elements> medsComponents9();

    @Query(value = "SELECT e.* " +
            "FROM ((medicine_elements AS me " +
            "INNER JOIN medicine m ON me.med_id = m.id) " +
            "INNER JOIN elements e on me.elem_id = e.id) " +
            "WHERE m.id =?1",
            nativeQuery = true)
    List<Elements> elementsByMedsName11_13(Integer id);
}