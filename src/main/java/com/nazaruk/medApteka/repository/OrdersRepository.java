package com.nazaruk.medApteka.repository;

import com.nazaruk.medApteka.model.Orders;
import com.nazaruk.medApteka.repository.projections.OrdersRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersRepository extends PagingAndSortingRepository<Orders, Integer>,
        OrdersRepositoryCustom<Orders> {
    Page<Orders> findAll(Pageable pageable);
}
