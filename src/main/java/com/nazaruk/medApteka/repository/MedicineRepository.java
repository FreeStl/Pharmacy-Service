package com.nazaruk.medApteka.repository;


import com.nazaruk.medApteka.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    @Query(value = "SELECT * " +
            "FROM medicine " +
            "WHERE (?1 IS NULL) OR (class = ?1) " +
            "ORDER BY sold_count DESC " +
            "LIMIT 10",
            nativeQuery = true)
    List<Medicine> popularMedsTop3(String medClass);

    @Query(value = "SELECT COUNT(*)\n" +
            "FROM medicine m \n" +
            "INNER JOIN orders AS o ON m.id = o.medicine_id \n" +
            "WHERE m.name=:name AND o.status IN ('READY', 'BOUGHT') \n" +
            "   AND o.created_at BETWEEN :frm AND :till\n",
            nativeQuery = true)
    Integer medsUsedForPeriod4(@Param("name") String name,
                               @Param("frm") Date from,
                               @Param("till") Date till
    );

    @Query(value = "SELECT * " +
                "FROM medicine " +
                "WHERE (:class IS NULL) OR (class = :class) " +
                "AND amount <=:critical",
            nativeQuery = true)
    List<Medicine> outOfMeds67(@Param("class") String medClass,
                                         @Param("critical") Integer criticalAmount);

    @Query(value = "SELECT * FROM medicine WHERE name LIKE CONCAT('%',?1,'%')",
            nativeQuery = true)
    List<Medicine>  findByName(String name);

}
