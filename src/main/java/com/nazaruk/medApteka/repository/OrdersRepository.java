package com.nazaruk.medApteka.repository;

import com.nazaruk.medApteka.model.Orders;
import com.nazaruk.medApteka.repository.projections.OrdersRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>,
        OrdersRepositoryCustom<Orders> {
    @Override
    @Query(value = "SELECT * FROM orders ORDER BY updated_at DESC "
            ,nativeQuery = true)
    List<Orders> findAll();
}
