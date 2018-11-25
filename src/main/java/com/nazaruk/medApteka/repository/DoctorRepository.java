package com.nazaruk.medApteka.repository;

import com.nazaruk.medApteka.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query(value = "SELECT *\n" +
            "  FROM doctor\n" +
            "  WHERE name = :name\n" +
            "    AND surname = :surname\n" +
            "    AND midname = :midname\n;",
            nativeQuery = true)
    Optional<Doctor> checkIfExist(@Param("name") String name,
                          @Param("surname") String surname,
                          @Param("midname") String midname);
}
