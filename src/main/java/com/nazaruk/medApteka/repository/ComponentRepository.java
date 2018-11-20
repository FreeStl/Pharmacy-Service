package com.nazaruk.medApteka.repository;

import com.nazaruk.medApteka.model.Component;
import com.nazaruk.medApteka.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Integer> {
    @Query(value = "SELECT * " +
            "FROM component AS c " +
            "WHERE c.id = ANY (SELECT mc.comp_id " +
                            "FROM(( medicine m " +
                            "INNER JOIN orders o ON m.id = o.medicine_id) " +
                            "INNER JOIN medicine_component mc ON m.id = mc.med_id) " +
                            "WHERE o.status = 'IN_PROD');",
            nativeQuery = true)
    List<Component> MedsComponents9();
}
