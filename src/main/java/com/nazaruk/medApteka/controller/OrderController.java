package com.nazaruk.medApteka.controller;


import com.nazaruk.medApteka.exeption.ResourceNotFoundException;
import com.nazaruk.medApteka.model.Doctor;
import com.nazaruk.medApteka.model.Orders;
import com.nazaruk.medApteka.repository.DoctorRepository;
import com.nazaruk.medApteka.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @PostMapping("/orders")
    public Orders createOrder(@Valid @RequestBody Orders order) {
       // Doctor doctor = doctorRepository.findById(order.getDoctor().getId())

        return ordersRepository.save(order);
    }

    @GetMapping("/orders/{id}")
    public Orders getOrderById(@PathVariable(value = "id") Integer orderId) {
        return ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    }

    @PutMapping("/orders/{id}")
    public Orders updateOrder(@PathVariable(value = "id") Integer orderId,
                                 @Valid @RequestBody Orders orderDetails) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", orderId));

        order.setDoctor(orderDetails.getDoctor());
        order.setPatient(orderDetails.getPatient());
        order.setReceipt(orderDetails.getReceipt());
        order.setMedicine((orderDetails.getMedicine()));
        order.setOrderStatus(orderDetails.getOrderStatus());

        Orders updatedOrder = ordersRepository.save(order);
        return updatedOrder;
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Integer orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", orderId));

        ordersRepository.delete(order);

        return ResponseEntity.ok().build();
    }
}
