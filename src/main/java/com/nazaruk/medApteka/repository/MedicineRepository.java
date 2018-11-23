package com.nazaruk.medApteka.repository;


import com.nazaruk.medApteka.model.Medicine;
import com.nazaruk.medApteka.model.entityEnums.MedClass;
import com.nazaruk.medApteka.repository.projections.MedicineRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer>,
        MedicineRepositoryCustom<Medicine> {
    @Query(value = "SELECT name, sold_count\n" +
            "FROM medicine\n" +
            "ORDER BY SoldCount DESC\n" +
            "LIMIT 10;",
            nativeQuery = true)
    List<Medicine> popularMedsTop3();

    @Query(value = "SELECT name, sold_count\n" +
            "FROM medicine\n" +
            "WHERE class =?1 \n" +
            "ORDER BY SoldCount DESC\n" +
            "LIMIT 10;",
            nativeQuery = true)
    List<Medicine> popularMedsTop3(MedClass medClass);

    @Query(value = "SELECT COUNT(*)\n" +
            "FROM medicine m \n" +
            "INNER JOIN orders AS o ON m.id = o.medicine_id \n" +
            "WHERE m.name=:name AND o.status IN (READY, BOUGHT) \n" +
            "   AND o.created_at BETWEEN :frm AND :till;\n",
            nativeQuery = true)
    Integer MedsUsedForPeriod4(@Param("name") String name,
                                             @Param("frm") Date from,
                                             @Param("till") Date till);

    @Query(value = "SELECT name, class\n" +
            "    FROM medicine\n" +
            "    WHERE amout <= ?1;",
            nativeQuery = true)
    List<Medicine> OutOfMeds67(Integer criticalAmount);

    @Query(value = "SELECT name, class\n" +
                "FROM medicine\n" +
                "WHERE class =:class AND amout <=:critical;",
            nativeQuery = true)
    List<Medicine> OutOfMeds67(@Param("class") MedClass medClass,
                                         @Param("critical") Integer criticalAmount);

    @Query(value = "SELECT * FROM medicine WHERE name LIKE CONCAT(?1,'%')",
            nativeQuery = true)
    List<Medicine>  findByName(String name);

}
