package com.nazaruk.medApteka.repository;

import com.nazaruk.medApteka.model.Orders;
import com.nazaruk.medApteka.repository.projections.OrdersRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>,
        OrdersRepositoryCustom<Orders> {
}
