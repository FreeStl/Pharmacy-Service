package com.nazaruk.medApteka.repository.projections;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface OrdersRepositoryCustom<Orders> {
    List<Object[]> ordersInProd8();
}
