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
            "FROM(( medicine m " +
            "INNER JOIN orders o ON m.id = o.medicine_id) " +
            "INNER JOIN medicine_elements me ON m.id = me.med_id) " +
            "WHERE o.status = 'IN_PROD');",
            nativeQuery = true)
    List<Elements> MedsComponents9();
}